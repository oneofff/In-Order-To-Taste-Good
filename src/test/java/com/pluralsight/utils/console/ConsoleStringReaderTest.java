package com.pluralsight.utils.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleStringReaderTest {

    private InputStream originalIn;


    private void setInput(String data) {
        originalIn = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ConsoleStringReader.scanner.close();
        ConsoleStringReader.scanner.reset();
        ConsoleStringReader.scanner = new java.util.Scanner(System.in);
    }

    @AfterEach
    void restoreSystemIn() {
        if (originalIn != null) {
            System.setIn(originalIn);
        }
    }

    @Test
    @DisplayName("getIntInRangeWithMargin() accepts first in-range integer")
    void intRange_firstTry() {
        setInput("2\n");
        int v = ConsoleStringReader.getIntInRangeWithMargin(1, 3);
        assertEquals(2, v);
    }

    @Test
    @DisplayName("getIntInRangeWithMargin() retries after out-of-range & non-numeric")
    void intRange_retry() {
        setInput("0\nabc\n3\n");
        int v = ConsoleStringReader.getIntInRangeWithMargin(1, 3);
        assertEquals(3, v);
    }

    @Test
    @DisplayName("getIntInRangeOfCollection() adapts range when zero-exit option")
    void intRangeCollection_withZeroExit() {
        setInput("0\n");
        int v = ConsoleStringReader.getIntInRangeOfCollection(List.of("A", "B", "C"), true);
        assertEquals(0, v);
    }

    @Test
    @DisplayName("getStringWithMargin() returns raw line")
    void stringWithMargin() {
        setInput("Hello world\n");
        String s = ConsoleStringReader.getStringWithMargin();
        assertEquals("Hello world", s);
    }
}