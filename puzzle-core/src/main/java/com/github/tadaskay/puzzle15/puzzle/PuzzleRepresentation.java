package com.github.tadaskay.puzzle15.puzzle;

import com.github.tadaskay.puzzle15.lang.HalResource;

import java.util.List;

class PuzzleRepresentation extends HalResource {

    private int size;
    private List<List<Integer>> tiles;
    private boolean complete;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Integer>> getTiles() {
        return tiles;
    }

    public void setTiles(List<List<Integer>> tiles) {
        this.tiles = tiles;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
