package com.github.tadaskay.puzzle15;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

class Puzzle {

    @Id
    private String id;

    private int side;
    private int[][] tiles;

    private Position blankPosition;

    Puzzle(int side, int[][] initialState) {
        assertValidPuzzle(side, initialState);
        this.tiles = LangUtil.deepCloneSquare(initialState);
        this.side = side;
        this.blankPosition = find(0);
    }

    private static void assertValidPuzzle(int side, int[][] initialState) {
        Assert.isTrue(side > 0, "Puzzle side must be a positive integer");
        Assert.isTrue(initialState.length == side, "Puzzle must be square with side=" + side);
        for (int row = 0; row < side; row++) {
            Assert.isTrue(initialState[row].length == side, "Puzzle must be square with side=" + side);
        }
    }

    private Position find(int number) {
        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                if (tiles[row][column] == number) {
                    return new Position(column, row);
                }
            }
        }
        return null;
    }

    int[][] getTiles() {
        return tiles;
    }

    boolean isCompleted() {
        int lastNumber = 0;
        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                int number = tiles[row][column];
                if (number > lastNumber) {
                    lastNumber = number;
                    continue;
                }

                boolean isBlank = number == 0;
                return last(row) && last(column) && isBlank;
            }
        }
        return true;
    }

    boolean canMoveUp() {
        return !last(blankPosition.y());
    }

    void moveUp() {
    }

    boolean canMoveDown() {
        return !first(blankPosition.y());
    }

    void moveDown() {
    }

    boolean canMoveLeft() {
        return !last(blankPosition.x());
    }

    void moveLeft() {
    }

    boolean canMoveRight() {
        return !first(blankPosition.x());
    }

    void moveRight() {
    }

    private boolean first(int rowOrColumn) {
        return rowOrColumn == 0;
    }

    private boolean last(int rowOrColumn) {
        return rowOrColumn == side - 1;
    }

}
