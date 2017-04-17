package com.github.tadaskay.puzzle15;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PuzzleTest {

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateNegativeSidePuzzle() {
        new Puzzle(-1, new int[][]{
            new int[]{0, 1},
            new int[]{3, 2},
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCreatePuzzleWithMismatchingDimensions() {
        new Puzzle(3, new int[][]{
            new int[]{0, 1},
            new int[]{3, 2},
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
        Puzzle puzzle = new Puzzle(2, shuffle);
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
