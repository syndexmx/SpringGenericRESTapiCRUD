package com.github.syndexmx.genericspring.annotations.processor;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.annotations.processor.directory.DirectoryWalker;

import javax.swing.*;
import java.io.File;
import java.lang.annotation.Annotation;

public class AnnotationProcessor {

    protected static class TemplatedAnnotationImpl implements TemplatedAnnotation {

        @Override
        public Class<? extends Annotation> annotationType() {
            return null;
        }
    }

    public static void main(String[] args) {
        JFileChooser sourceDirectoryChooser = new JFileChooser();
        sourceDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int sourceValue = sourceDirectoryChooser.showOpenDialog(null);

        JFileChooser resultDirectoryChooser = new JFileChooser();
        resultDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultValue = resultDirectoryChooser.showSaveDialog(null);

        if(sourceValue == JFileChooser.APPROVE_OPTION && resultValue == JFileChooser.APPROVE_OPTION) {
            String sourceDirectory = sourceDirectoryChooser.getSelectedFile().getAbsolutePath();
            String resultDirectory = resultDirectoryChooser.getSelectedFile().getAbsolutePath();
            try {
                DirectoryWalker walker = new DirectoryWalker("", resultDirectory,  new File(sourceDirectory));
                walker.walkOverSubdirectories();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
