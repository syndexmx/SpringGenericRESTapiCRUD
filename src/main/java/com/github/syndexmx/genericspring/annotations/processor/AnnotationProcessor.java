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
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String chosenDirectory = chooser.getSelectedFile().getAbsolutePath();
            try {
                DirectoryWalker walker = new DirectoryWalker("", new File(chosenDirectory));
                walker.walkOverSubdirectories();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
