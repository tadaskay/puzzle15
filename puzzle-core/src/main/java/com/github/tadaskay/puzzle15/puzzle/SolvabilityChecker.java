package com.github.tadaskay.puzzle15.puzzle;

import org.springframework.stereotype.Component;

import static com.github.tadaskay.puzzle15.lang.ArrayUtil.flatten;

interface SolvabilityChecker {
    boolean isSolvable(Integer[][] tiles);
}

/**
 * Props: http://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
 */
@Component
class DefaultSolvabilityChecker implements SolvabilityChecker {

    @Override
    public boolean isSolvable(Integer[][] tiles) {
        boolean isSizeEven = tiles.length % 2 == 0;
        int inversionCount = inversionCount(tiles);
        boolean inversionCountEven = inversionCount % 2 == 0;

        if (isSizeEven) {
            int blankOnRow = findRowOfTheBlank(tiles);
            int blankOnRowFromTheBottom = tiles.length - blankOnRow;
            boolean blankOnEvenRow = blankOnRowFromTheBottom % 2 == 0;

            if (blankOnEvenRow && !inversionCountEven) {
                return true;
            }
            if (!blankOnEvenRow && inversionCountEven) {
                return true;
            }
        } else {
            if (inversionCountEven) {
                return true;
            }
        }

        return false;
    }

    private int inversionCount(Integer[][] tiles) {
        Integer[] flat = flatten(tiles);
        int size = tiles.length;

        int count = 0;
        for (int i = 0; i < size * size - 1; i++) {
            for (int j = i + 1; j < size * size; j++) {
                boolean neitherIsBlankTile = flat[j] != 0 && flat[i] != 0;
                if (neitherIsBlankTile && flat[i] > flat[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private Integer findRowOfTheBlank(Integer[][] orig) {
        for (int row = 0; row < orig.length; row++) {
            for (int col = 0; col < orig[row].length; col++) {
                if (orig[row][col] == 0) {
                    return row;
                }
            }
        }
        throw new IllegalStateException("Blank tile not found");
    }
}
