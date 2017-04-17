package com.github.tadaskay.puzzle15;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PuzzleTest {

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateEmptyPuzzle() {
        Puzzle.create(new int[][]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCreatePuzzleWithMismatchingDimensions() {
        Puzzle.create(new int[][]{
            new int[]{0, 1},
            new int[]{4, 2, 5},
            new int[]{6, 7, 3},
        });
    }

    @Test
    public void solvable2x2puzzle() {
        // given
        int[][] shuffle = {
            new int[]{0, 1},
            new int[]{3, 2},
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
