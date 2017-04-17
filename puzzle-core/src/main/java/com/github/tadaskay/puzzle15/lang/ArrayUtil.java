package com.github.tadaskay.puzzle15.lang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ArrayUtil {

    public static <T> List<List<T>> array2dToList(T[][] array) {
        List<List<T>> result = new ArrayList<>();
        for (T[] row : array) {
            result.add(Arrays.asList(row));
        }
        return result;
    }

    /**
     * NOTE: Assumes square
     */
    public static <T> T[][] list2dToArray(List<List<T>> list, Class<T> componentType) {
        T[][] array2d = (T[][]) Array.newInstance(componentType, list.size(), list.size());
        for (int row = 0; row < list.size(); row++) {
            T[] rowToArray = (T[]) list.get(row).toArray();
            array2d[row] = rowToArray;
        }
        return array2d;
    }

    public static Integer[] flatten(Integer[][] original) {
        Integer[] flat = new Integer[original.length * original.length];
        for (int row = 0; row < original.length; row++) {
            System.arraycopy(original[row], 0, flat, original.length * row, original.length);
        }
        return flat;
    }
}
