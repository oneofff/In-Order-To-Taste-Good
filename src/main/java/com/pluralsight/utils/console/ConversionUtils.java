package com.pluralsight.utils.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConversionUtils {

    public static <T, L> List<String> mapToIndexedList(Map<T, L> sizes) {
        List<String> out = new ArrayList<>();
        int i = 1;
        for (Map.Entry<T, L> e : sizes.entrySet()) {
            out.add(String.format("%d. %s $%s", i++, e.getKey(), e.getValue()));
        }
        return out;
    }

    public static <T> List<String> listToIndexedList(List<T> list) {
        List<String> out = new ArrayList<>();
        int i = 1;
        for (T e : list) {
            out.add(String.format("%d. %s", i++, e));
        }
        return out;
    }
}
