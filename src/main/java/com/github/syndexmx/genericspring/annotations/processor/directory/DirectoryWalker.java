package com.github.syndexmx.genericspring.annotations.processor.directory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.genericspring.annotations.processor.directory.Filewalker.processFile;

public class DirectoryWalker {

    private String prefix;
    private String resultPrefix;
    private File directoryName;
    static final List<String> DIR_IGNORE_LIST = Arrays.asList(".git");

    static final List<String> FILES_PROCESSED_LIST = Arrays.asList(".java");


    public DirectoryWalker(String prefix, String resultPrefix, File directoryName) {
        this.prefix = prefix;
        this.directoryName = directoryName;
        this.resultPrefix = resultPrefix;
    }

    public void walkOverSubdirectories() throws Exception {
        for (File file : directoryName.listFiles()) {
            if (file.isDirectory()) {
                boolean isSkippable = false;
                for (String s : DIR_IGNORE_LIST) {
                    if (s.equals(cutDirectoryName(file.toString()))) isSkippable = true;
                }
                if (!isSkippable) {
                    DirectoryWalker subWalker =
                            new DirectoryWalker(prefix + "\t",
                                    resultPrefix + File.separator + (file.getName()),
                                    file);
                    subWalker.walkOverSubdirectories();
                }
            } else if (file.isFile()) {
                boolean isProcessedType = false;
                for (String s : FILES_PROCESSED_LIST) {
                    if (cutDirectoryName(file.toString()).contains(s)) isProcessedType = true;
                }
                if (isProcessedType) {
                    Optional<String> optionalResultClass = processFile(file);
                    if (optionalResultClass.isPresent()) {
                        String classText = optionalResultClass.get();
                        String path = StringProcessor.processLine(resultPrefix);
                        String fileName = StringProcessor.processLine(file.getName());
                        System.out.println(path + File.separator + fileName);
                        TargetClassSaver.saveClass(classText, path, fileName);
                    }
                }

            }
        }
    }

    public static String cutDirectoryName(String fullPath) {
        return fullPath.substring(fullPath.lastIndexOf(File.separator) + 1);
    }


}