package com.github.tadaskay.puzzle15.puzzle;

import com.github.tadaskay.puzzle15.lang.ArrayUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Generator {
    Integer[][] createSolvedState();
    Integer[][] generateTiles();
}

@Component
class DefaultGenerator implements Generator {

    private static final int SIZE = 4;
    private static final int USE_AS_BLANK = 0;
    private static final int SHUFFLE_ITERATIONS = 142;
    private static final Random random = new Random();

    DefaultGenerator() {
    }

    @Override
    public Integer[][] createSolvedState() {
        Integer[][] solvedState = new Integer[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                solvedState[row][col] = row * SIZE + (col + 1);
            }
        }
        solvedState[SIZE - 1][SIZE - 1] = USE_AS_BLANK;
        return solvedState;
    }

    @Override
    public Integer[][] generateTiles() {
        Puzzle puzzle = Puzzle.create(createSolvedState());

        for (int i = 0; i < SHUFFLE_ITERATIONS; i++) {
            List<Runnable> paths = availablePaths(puzzle);
            Runnable destiny = paths.get(random.nextInt(paths.size()));
            destiny.run();
        }

        Integer[][] shuffledTiles = ArrayUtil.list2dToArray(puzzle.getTiles(), Integer.class);
        return shuffledTiles;
    }

    private List<Runnable> availablePaths(Puzzle puzzle) {
        List<Runnable> paths = new ArrayList<>();
        if (puzzle.canMoveUp()) {
            paths.add(puzzle::moveUp);
        }
        if (puzzle.canMoveRight()) {
            paths.add(puzzle::moveRight);
        }
        if (puzzle.canMoveDown()) {
            paths.add(puzzle::moveDown);
        }
        if (puzzle.canMoveLeft()) {
            paths.add(puzzle::moveLeft);
        }
        return paths;
    }
}
