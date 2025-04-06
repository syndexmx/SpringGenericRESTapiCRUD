package com.github.syndexmx.genericspring.annotations.processor.directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.github.syndexmx.genericspring.annotations.processor.directory.StringProcessor.processLine;

public class AnnotatedFileProcessor {

    public static String processAnnotatedFile(File file) {
        StringBuilder classBuilder = new StringBuilder();
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String line = bReader.readLine();
            while (line != null) {
                if (line.contains("@TemplatedAnnotation{")) {
                    StringBuilder blockBuilder = new StringBuilder();
                    String nextBlockLine = bReader.readLine();
                    boolean isFirstLine = true;
                    while (!nextBlockLine.contains("@TemplatedAnnotation}")) {
                        if (!isFirstLine) {
                            blockBuilder.append("\n");
                        }
                        blockBuilder.append(nextBlockLine);
                        nextBlockLine = bReader.readLine();
                        isFirstLine = false;
                    }
                    line = blockBuilder.toString();
                }
                classBuilder.append(processLine(line) + "\n");
                line = bReader.readLine();
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classBuilder.toString();
    }
}
