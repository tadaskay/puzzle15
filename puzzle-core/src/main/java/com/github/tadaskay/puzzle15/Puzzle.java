package com.github.tadaskay.puzzle15;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

class Puzzle {

    @Id
    private String id;

    private int side;
    private int[][] tiles;

    private Position blankPosition;

    Puzzle(int[][] initialState) {
        this.blankPosition = validate(initialState);
        this.side = initialState.length;
        this.tiles = LangUtil.deepCloneSquare(initialState);
    }

    /**
     * Validates state in a single pass and return the position of the blank
     */
    private static Position validate(int[][] state) {
        int side = state.length;
        Assert.isTrue(side > 0, "Puzzle's state cannot be empty");

        Position blankPosition = null;
        Set<Integer> seenNumbers = new HashSet<>();

        for (int row = 0; row < side; row++) {
            Assert.isTrue(state[row].length == side, "Puzzle must be square with side=" + side);

            for (int column = 0; column < side; column++) {
                int number = state[row][column];
                Assert.isTrue(!seenNumbers.contains(number), "Puzzle cannot contain duplicate numbers");
                seenNumbers.add(number);

                if (number == 0) {
                    blankPosition = new Position(column, row);
                }
            }
        }

        Assert.notNull(blankPosition, "There is no blank tile");
        return blankPosition;
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
        moveBlankTile(0, 1);
    }

    boolean canMoveDown() {
        return !first(blankPosition.y());
    }

    void moveDown() {
        moveBlankTile(0, -1);
    }

    boolean canMoveLeft() {
        return !last(blankPosition.x());
    }

    void moveLeft() {
        moveBlankTile(1, 0);
    }

    boolean canMoveRight() {
        return !first(blankPosition.x());
    }

    void moveRight() {
        moveBlankTile(-1, 0);
    }

    private boolean first(int rowOrColumn) {
        return rowOrColumn == 0;
    }

    private boolean last(int rowOrColumn) {
        return rowOrColumn == side - 1;
    }

    private void moveBlankTile(int dx, int dy) {
        Position numberPosition = blankPosition.moveX(dx).moveY(dy);
        swapTiles(numberPosition, blankPosition);
        blankPosition = numberPosition;
    }

    private void swapTiles(Position p1, Position p2) {
        int num1 = tiles[p1.y()][p1.x()];
        int num2 = tiles[p2.y()][p2.x()];
        tiles[p1.y()][p1.x()] = num2;
        tiles[p2.y()][p2.x()] = num1;
    }
}
