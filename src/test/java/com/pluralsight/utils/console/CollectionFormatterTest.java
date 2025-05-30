package com.pluralsight.utils.console;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


class CollectionFormatterTest {


    @Test
    @DisplayName("mapToIndexedList() returns 1-based numbered lines in insertion order")
    void testMapToIndexedList() {
        //given
        Map<String, Double> sizes = new LinkedHashMap<>();
        sizes.put("4inch", 5.50);
        sizes.put("8inch", 7.00);
        sizes.put("12inch", 8.50);

        //when
        List<String> actual = CollectionFormatter.mapToIndexedList(sizes);

        //then
        List<String> expected = List.of(
                "1. 4inch $5.50",
                "2. 8inch $7.00",
                "3. 12inch $8.50"
        );
        assertIterableEquals(expected, actual, "Lines should match expected numbering and formatting");
    }

    /* ------------------------------------------------------------------
     * listToMenu (with exit option)
     * ------------------------------------------------------------------ */

    @Test
    @DisplayName("listToMenu() adds a 0-exit choice and numbers the rest")
    void testListToMenuWithExit() {
        // given
        List<String> toppings = List.of("Bacon", "Avocado");
        String exit = "Cancel";

        // when
        List<String> actual = CollectionFormatter.listToMenu(
                toppings,
                s -> String.format("%s ($1.00)", s),
                exit
        );

        // then
        List<String> expected = List.of(
                "1. Bacon ($1.00)",
                "2. Avocado ($1.00)",
                "0. Cancel"
        );
        assertIterableEquals(expected, actual);
    }

    @Test
    @DisplayName("listToMenu() without exit returns indexed items only")
    void testListToMenuNoExit() {
        // given
        List<Integer> numbers = List.of(10, 20, 30);

        // when
        List<String> actual = CollectionFormatter.listToMenu(
                numbers,
                n -> "Num=" + n
        );

        // then
        List<String> expected = List.of(
                "1. Num=10",
                "2. Num=20",
                "3. Num=30"
        );
        assertIterableEquals(expected, actual);
    }
}
