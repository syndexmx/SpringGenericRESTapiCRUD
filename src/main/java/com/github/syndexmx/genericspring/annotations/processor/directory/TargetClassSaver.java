package com.github.syndexmx.genericspring.annotations.processor.directory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TargetClassSaver {

    public static void saveClass(String classText, String path, String fileName) {
        try {
            new File(path).mkdirs();

            File file = new File(path + File.separator + fileName);
            FileWriter output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write(classText);
            writer.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
