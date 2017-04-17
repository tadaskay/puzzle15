package com.github.tadaskay.puzzle15;

import org.springframework.stereotype.Component;

interface Generator {

    int[][] generateTiles();

}

@Component
class DefaultGenerator implements Generator {

    @Override
    public int[][] generateTiles() {
        return new int[][]{
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{0, 7, 8},
        };
    }
}
