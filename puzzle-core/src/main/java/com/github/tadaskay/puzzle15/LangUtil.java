package com.github.tadaskay.puzzle15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LangUtil {

    static <T> List<List<T>> array2dToList(T[][] array) {
        List<List<T>> result = new ArrayList<>();
        for (T[] row : array) {
            result.add(Arrays.asList(row));
        }
        return result;
    }

    static Integer[] flatten(Integer[][] original) {
        Integer[] flat = new Integer[original.length * original.length];
        for (int row = 0; row < original.length; row++) {
            System.arraycopy(original[row], 0, flat, original.length * row, original.length);
        }
        return flat;
    }
}
