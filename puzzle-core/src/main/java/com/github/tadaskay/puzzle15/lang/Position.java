package com.github.tadaskay.puzzle15.lang;

public class Position {

    private final int x, y;

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Position moveX(int delta) {
        return new Position(x + delta, y);
    }

    public Position moveY(int delta) {
        return new Position(x, y + delta);
    }
}
