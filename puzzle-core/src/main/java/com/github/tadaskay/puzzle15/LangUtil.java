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
}
