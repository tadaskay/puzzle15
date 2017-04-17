package com.github.tadaskay.puzzle15;

import com.github.tadaskay.puzzle15.lang.HalResource;
import com.github.tadaskay.puzzle15.puzzle.PuzzleResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
class ApiIndexResource {

    @GetMapping("/api")
    public HalResource index() {
        HalResource apiIndex = new HalResource();
        apiIndex.add(linkTo(methodOn(PuzzleResource.class).newPuzzle(null)).withRel("new-puzzle"));
        return apiIndex;
    }
}
