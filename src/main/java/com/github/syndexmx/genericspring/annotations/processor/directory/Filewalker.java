package com.github.syndexmx.genericspring.annotations.processor.directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static com.github.syndexmx.genericspring.annotations.processor.directory.AnnotatedFileProcessor.processAnnotatedFile;

public class Filewalker {

    public static Optional<String> processFile(File file) {

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String line = bReader.readLine();
            boolean isAnnotated = false;
            while (line != null) {
                if (line.indexOf("@TemplatedAnnotation", 0) == 0) {
                    isAnnotated = true;
                    break;
                }
                line = bReader.readLine();
            }
            bReader.close();
            if (isAnnotated) {
                String classFile = processAnnotatedFile(file);
                return Optional.of(classFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}