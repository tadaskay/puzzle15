package com.github.tadaskay.puzzle15;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.tadaskay.puzzle15.LangUtil.array2dToList;

class Puzzle {

    @Id
    private String id;
    private int size;
    private List<List<Integer>> tiles;

    private Position blankPosition;

    static Puzzle create(Integer[][] initialTiles) {
        Puzzle puzzle = new Puzzle();
        puzzle.blankPosition = validate(initialTiles);
        puzzle.size = initialTiles.length;
        puzzle.tiles = array2dToList(initialTiles);
        return puzzle;
    }

    /**
     * Validates state in a single pass and returns the position of the blank
     */
    private static Position validate(Integer[][] state) {
        int size = state.length;
        Assert.isTrue(size > 0, "Puzzle's state cannot be empty");

        Position blankPosition = null;
        Set<Integer> seenNumbers = new HashSet<>();

        for (int row = 0; row < size; row++) {
            Assert.isTrue(state[row].length == size, "Puzzle must be square");

            for (int column = 0; column < size; column++) {
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

    boolean isCompleted() {
        int lastNumber = 0;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                int number = getTileAt(Position.of(column, row));
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
        return rowOrColumn == size - 1;
    }

    private void moveBlankTile(int dx, int dy) {
        Position numberPosition = blankPosition.moveX(dx).moveY(dy);
        swapTiles(numberPosition, blankPosition);
        blankPosition = numberPosition;
    }

    private void swapTiles(Position p1, Position p2) {
        int num1 = getTileAt(p1);
        int num2 = getTileAt(p2);
        setTileAt(p1, num2);
        setTileAt(p2, num1);
    }

    private int getTileAt(Position pos) {
        return tiles.get(pos.y()).get(pos.x());
    }

    private void setTileAt(Position pos, Integer value) {
        tiles.get(pos.y()).set(pos.x(), value);
    }

    public int getSize() {
        return size;
    }

    public String getId() {
        return id;
    }

    public List<List<Integer>> getTiles() {
        return tiles;
    }
}
