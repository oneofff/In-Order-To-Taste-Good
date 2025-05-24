package com.pluralsight.utils.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtils implements AutoCloseable {
    private final BufferedReader bufferedReader;

    public FileReaderUtils(String file) {
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isEmpty() {
        try {
            bufferedReader.mark(1);
            int ch = bufferedReader.read();
            bufferedReader.reset();
            return ch == -1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
