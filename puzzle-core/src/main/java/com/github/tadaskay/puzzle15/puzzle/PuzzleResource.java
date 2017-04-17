package com.github.tadaskay.puzzle15.puzzle;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.github.tadaskay.puzzle15.puzzle.PuzzleResource.Direction.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
class PuzzleResource {

    private final PuzzleRepository repository;
    private final Generator generator;
    private final SolvabilityChecker checker;

    PuzzleResource(PuzzleRepository repository,
                   Generator generator,
                   SolvabilityChecker checker) {
        this.repository = repository;
        this.generator = generator;
        this.checker = checker;
    }

    @PostMapping("/puzzles")
    ResponseEntity<PuzzleRepresentation> newPuzzle(UriComponentsBuilder uriBuilder) {
        Integer[][] generated = generator.generateTiles();
        Assert.isTrue(checker.isSolvable(generated), "Unsolvable puzzle tiles have been generated");
        Puzzle puzzle = Puzzle.create(generated);
        puzzle = repository.save(puzzle);

        URI location = uriBuilder.path("/urls/{id}").buildAndExpand(puzzle.getId()).toUri();
        return ResponseEntity.created(location).body(represent(puzzle));
    }

    @GetMapping("/puzzles/{id}")
    ResponseEntity<PuzzleRepresentation> get(@PathVariable("id") String id) {
        Puzzle puzzle = repository.requireOne(id);
        return ResponseEntity.ok(represent(puzzle));
    }

    @PutMapping("/puzzles/{id}/move/{direction}")
    ResponseEntity<PuzzleRepresentation> move(@PathVariable("id") String id,
                                              @PathVariable("direction") Direction direction) {
        Puzzle puzzle = repository.requireOne(id);
        switch (direction) {
            case up:
                puzzle.moveUp();
                break;
            case down:
                puzzle.moveDown();
                break;
            case right:
                puzzle.moveRight();
                break;
            case left:
                puzzle.moveLeft();
                break;
        }
        puzzle = repository.save(puzzle);
        return ResponseEntity.ok(represent(puzzle));
    }

    private static PuzzleRepresentation represent(Puzzle puzzle) {
        PuzzleRepresentation rep = new PuzzleRepresentation();
        rep.setSize(puzzle.getSize());
        rep.setTiles(puzzle.getTiles());
        rep.setComplete(puzzle.isCompleted());
        if (puzzle.canMoveUp()) {
            rep.add(linkTo(methodOn(PuzzleResource.class).move(puzzle.getId(), up)).withRel("move-up"));
        }
        if (puzzle.canMoveDown()) {
            rep.add(linkTo(methodOn(PuzzleResource.class).move(puzzle.getId(), down)).withRel("move-down"));
        }
        if (puzzle.canMoveRight()) {
            rep.add(linkTo(methodOn(PuzzleResource.class).move(puzzle.getId(), right)).withRel("move-right"));
        }
        if (puzzle.canMoveLeft()) {
            rep.add(linkTo(methodOn(PuzzleResource.class).move(puzzle.getId(), left)).withRel("move-left"));
        }
        return rep;
    }

    enum Direction {
        up, down, right, left
    }
}
