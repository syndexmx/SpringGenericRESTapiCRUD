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

        // Fields !!!!
        if (current.contains(TemplatedAnnotation.genericDependencyType)) {
            current = substitute(current,
                    TemplatedAnnotation.genericDependencyType,
                    TemplatedAnnotation.targetDependencyType);
        }
        if (current.contains(TemplatedAnnotation.genericDependencyVariable)) {
            current = substitute(current,
                    TemplatedAnnotation.genericDependencyVariable,
                    TemplatedAnnotation.targetDependencyVariable);
        }
        if (current.contains(TemplatedAnnotation.genericFieldType)
                || current.contains(TemplatedAnnotation.genericFieldName)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String fieldName : TemplatedAnnotation.fieldsMap.keySet()) {
                String one = substitute(current,
                        TemplatedAnnotation.genericFieldType,
                        TemplatedAnnotation.fieldsMap.get(fieldName));
                one = substitute(one,
                        TemplatedAnnotation.genericFieldName,
                        fieldName);
                stringBuilder.append(one + "\n");
            }
            current = stringBuilder.toString();
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

        if (current.contains(TemplatedAnnotation.genericAllUpperCaseName)) {
            current = substitute(current,
                    TemplatedAnnotation.genericAllUpperCaseName,
                    TemplatedAnnotation.targetAllUpperCaseName);

        }

        return current;
    }

    private static String substitute(String source, String replaced, String replacement) {
        return source.replaceAll(replaced, replacement);
    }
}
