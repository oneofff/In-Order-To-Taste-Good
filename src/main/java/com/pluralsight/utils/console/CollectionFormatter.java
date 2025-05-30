package com.pluralsight.utils.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionFormatter {

    public static <T, L> List<String> mapToIndexedList(Map<T, L> sizes) {
        List<Map.Entry<T, L>> entries = new ArrayList<>(sizes.entrySet());

        return IntStream.range(0, entries.size())
                .mapToObj(i -> {
                    Map.Entry<T, L> e = entries.get(i);
                    return String.format("%d. %s $%.2f", i + 1, e.getKey(), e.getValue());
                })
                .collect(Collectors.toList());
    }

    private static <T> List<String> listToIndexedList(List<T> list) {
        List<String> out = new ArrayList<>();
        int i = 1;
        for (T e : list) {
            out.add(String.format("%d. %s", i++, e));
        }

        return out;
    }

    public static <T> List<String> listToMenu(List<T> items, Function<T, String> itemFormatter, String exitOption) {
        List<String> formattedStrings = items.stream()
                .map(itemFormatter)
                .collect(Collectors.toList());
        List<String> indexedList = CollectionFormatter.listToIndexedList(formattedStrings);
        indexedList.add(String.format("%d. %s", 0, exitOption));
        return indexedList;
    }

    public static <T> List<String> listToMenu(List<T> items, Function<T, String> itemFormatter) {
        List<String> formattedStrings = items.stream()
                .map(itemFormatter)
                .collect(Collectors.toList());
        return CollectionFormatter.listToIndexedList(formattedStrings);
    }
}
