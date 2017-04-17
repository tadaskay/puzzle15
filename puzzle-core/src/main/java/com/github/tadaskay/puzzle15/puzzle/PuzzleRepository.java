package com.github.tadaskay.puzzle15.puzzle;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface PuzzleRepository extends MongoRepository<Puzzle, String> {

    default Puzzle requireOne(String id) throws NotFoundException {
        return Optional.ofNullable(findOne(id))
            .orElseThrow(NotFoundException::new);
    }
}
