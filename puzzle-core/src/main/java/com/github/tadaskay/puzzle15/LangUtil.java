package com.github.tadaskay.puzzle15;

class LangUtil {

    static int[][] deepCloneSquare(int[][] array) {
        int length = array.length;
        int[][] clone = new int[length][length];
        for (int row = 0; row < length; row++) {
            System.arraycopy(array[row], 0, clone[row], 0, length);
        }
        return clone;
    }
}
