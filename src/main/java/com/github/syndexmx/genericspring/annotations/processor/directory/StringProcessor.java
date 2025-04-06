package com.github.syndexmx.genericspring.annotations.processor.directory;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class StringProcessor {

    private static String TEMPLATED_ANNOTATION = "TemplatedAnnotation";

    protected static String processLine(String sourceString) {

        String current = sourceString;

        if ((sourceString.indexOf("@" + TEMPLATED_ANNOTATION) == 0)
                || (sourceString.contains("import ") & sourceString.contains(TEMPLATED_ANNOTATION))) {
            return "";
        }

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

        // Fields !!!!
        Map<String, String> fieldsMap = TemplatedAnnotation.fieldsMap;
        Map<String, String> fieldsCapitalisedMap = new HashMap<String, String>();
        for (String string : fieldsMap.keySet()) {
            String stringCapitalized = StringUtils.capitalize(string);
            fieldsCapitalisedMap.put(string, stringCapitalized);
        }
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
            boolean firstRound = true;
            for (String fieldName : fieldsMap.keySet()) {
                if (!firstRound) {
                    stringBuilder.append("\n");
                }
                firstRound = false;
                String one = substitute(current,
                        TemplatedAnnotation.genericFieldName,
                        fieldName);
                one = substitute(one,
                        "get" + StringUtils.capitalize(TemplatedAnnotation.genericFieldName),
                        "get" + fieldsCapitalisedMap.get(fieldName));
                one = substitute(one,
                        TemplatedAnnotation.genericFieldType,
                        fieldsMap.get(fieldName));
                stringBuilder.append(one);
            }
            current = stringBuilder.toString();
        }
        // -- fields

        if (current.contains(TemplatedAnnotation.genericObjectLowCaseName)) {
            current = substitute(current,
                    TemplatedAnnotation.genericObjectLowCaseName,
                    TemplatedAnnotation.targetObjectLowCaseName);
        }


        if (current.contains(TemplatedAnnotation.genericType)) {
            current = substitute(current,
                    TemplatedAnnotation.genericType,
                    TemplatedAnnotation.targetType);

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
