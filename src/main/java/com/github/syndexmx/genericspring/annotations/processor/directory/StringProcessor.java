package com.github.syndexmx.genericspring.annotations.processor.directory;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;

public class StringProcessor {

    protected static String processLine(String sourceString) {

        String current = sourceString;

        // Package and Imports
        if (sourceString.contains(TemplatedAnnotation.genericPackageAddress)) {
            current = substitute(sourceString,
                    TemplatedAnnotation.genericPackageAddress,
                    TemplatedAnnotation.targetPackageAddress);
        }


        if (current.contains(TemplatedAnnotation.genericObjectClass)) {
            current = substitute(current,
                    TemplatedAnnotation.genericObjectClass,
                    TemplatedAnnotation.targetObjectClass);

        }

        if (current.contains("package ") || current.contains("import ")) {
            if (current.indexOf("package ") == 0 || current.indexOf("package ") == 0) {
                return current;
            }
        }

        if (current.contains(TemplatedAnnotation.genericObjectLowCaseName)) {
            current = substitute(current,
                    TemplatedAnnotation.genericObjectLowCaseName,
                    TemplatedAnnotation.targetObjectLowCaseName);
        }

        // Field !!!!
        if (current.contains(TemplatedAnnotation.genericFieldTypes)) {
            current = substitute(current,
                    TemplatedAnnotation.genericFieldTypes,
                    TemplatedAnnotation.targetFieldTypes);
        }
        if (current.contains(TemplatedAnnotation.genericFieldNames)) {
            current = substitute(current,
                    TemplatedAnnotation.genericFieldNames,
                    TemplatedAnnotation.targetFieldNames);
        }
        // -- fields


        if (current.contains(TemplatedAnnotation.genericName)) {
            current = substitute(current,
                    TemplatedAnnotation.genericName,
                    TemplatedAnnotation.targetName);

        }
        if (current.contains(TemplatedAnnotation.genericLowCaseName)) {
            current = substitute(current,
                    TemplatedAnnotation.genericLowCaseName,
                    TemplatedAnnotation.targetLowCaseName);

        }

        if (current.contains(TemplatedAnnotation.genericIdType)) {
            current = substitute(current,
                    TemplatedAnnotation.genericIdType,
                    TemplatedAnnotation.targetIdType);

        }

        return current;
    }

    private static String substitute(String source, String replaced, String replacement) {
        return source.replaceAll(replaced, replacement);
    }
}
