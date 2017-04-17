package com.github.tadaskay.puzzle15;

import org.springframework.stereotype.Component;

interface Generator {

    Integer[][] generateTiles();

}

@Component
class DefaultGenerator implements Generator {

    @Override
    public Integer[][] generateTiles() {
        return new Integer[][]{
            new Integer[]{1, 2, 3},
            new Integer[]{4, 5, 6},
            new Integer[]{0, 7, 8},
        };
    }
}
