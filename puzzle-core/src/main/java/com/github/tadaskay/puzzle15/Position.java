package com.github.tadaskay.puzzle15;

class Position {

    private final int x, y;

    static Position of(int x, int y) {
        return new Position(x, y);
    }

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }

    Position moveX(int delta) {
        return new Position(x + delta, y);
    }

    Position moveY(int delta) {
        return new Position(x, y + delta);
    }
}
