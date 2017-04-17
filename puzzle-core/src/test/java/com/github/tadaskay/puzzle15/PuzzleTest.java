package com.github.tadaskay.puzzle15;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PuzzleTest {

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateEmptyPuzzle() {
        Puzzle.create(new Integer[][]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCreatePuzzleWithMismatchingDimensions() {
        Puzzle.create(new Integer[][]{
            new Integer[]{0, 1},
            new Integer[]{4, 2, 5},
            new Integer[]{6, 7, 3},
        });
    }

    @Test
    public void solvable2x2puzzle() {
        // given
        Integer[][] shuffle = {
            new Integer[]{0, 1},
            new Integer[]{3, 2},
        };

        // when
        Puzzle puzzle = Puzzle.create(shuffle);
        // then
        assertFalse(puzzle.isCompleted());
        assertTrue(puzzle.canMoveLeft());
        assertTrue(puzzle.canMoveUp());

        // when
        puzzle.moveLeft();
        // then
        assertFalse(puzzle.isCompleted());
        assertTrue(puzzle.canMoveUp());
        assertTrue(puzzle.canMoveRight());

        // when
        puzzle.moveUp();
        // then
        assertTrue(puzzle.isCompleted());
    }
}
