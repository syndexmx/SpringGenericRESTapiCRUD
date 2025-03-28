package com.github.syndexmx.genericspring.annotations.processor.directory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.github.syndexmx.genericspring.annotations.processor.directory.Filewalker.processFile;

public class DirectoryWalker {

    String prefix;
    File directoryName;
    static final List<String> DIR_IGNORE_LIST = Arrays.asList(".git");

    static final List<String> FILES_PROCESSED_LIST = Arrays.asList(".java");


    public DirectoryWalker(String prefix, File directoryName) {
        this.prefix = prefix;
        this.directoryName = directoryName;
    }

    public void walkOverSubdirectories() throws Exception {
        for (File file : directoryName.listFiles()) {
            if (file.isDirectory()) {
                boolean isSkippable = false;
                for (String s : DIR_IGNORE_LIST) {
                    if (s.equals(cutDirectoryName(file.toString()))) isSkippable = true;
                }
                if (!isSkippable) {
                    DirectoryWalker subWalker = new DirectoryWalker(prefix + "\t", file);
                    subWalker.walkOverSubdirectories();
                }
            } else if (file.isFile()) {
                boolean isProcessedType = false;
                for (String s : FILES_PROCESSED_LIST) {
                    if (cutDirectoryName(file.toString()).contains(s)) isProcessedType = true;
                }
                if (isProcessedType) {
                    System.out.println(file.toString());
                    processFile(file);
                }

            }
        }
    }

    public static String cutDirectoryName(String fullPath) {
        return fullPath.substring(fullPath.lastIndexOf(File.separator) + 1);
    }


}