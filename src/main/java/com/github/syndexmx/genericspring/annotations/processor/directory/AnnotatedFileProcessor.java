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
