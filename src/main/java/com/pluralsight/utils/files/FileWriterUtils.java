package com.pluralsight.utils.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtils implements AutoCloseable {
    private BufferedWriter bufferedWriter;

    public FileWriterUtils(String file) {
        try {
            File newFile = new File(file);
            File parent = newFile.getParentFile();
            if (!parent.exists())
                if (!parent.mkdir()) {
                    throw new IOException("Could not create directory: " + parent);
                }

            this.bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public FileWriterUtils() {
    }

    public void writeLine(String line) {
        try {
            bufferedWriter.write(line + "\n");
            bufferedWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
