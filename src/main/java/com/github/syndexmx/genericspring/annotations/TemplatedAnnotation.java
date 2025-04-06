package com.github.syndexmx.genericspring.annotations;

import org.springframework.util.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static java.util.Map.entry;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface TemplatedAnnotation {

    String TARGET_CLASS_NAME = "Target"; // Change here

    String genericIdType = "UUID";
    String targetIdType = "UUID"; //change here

    String genericFieldType = "Short";
    String genericFieldName = "genericField";
    Map<String, String> fieldsMap = Map.ofEntries( // Object Fields block
            entry("targetLong", "Long"), // Change here
            entry("targetString", "String"), // Change here
            entry("targetInteger", "Integer"), // Change here
            entry("targetBoolean", "Boolean") // Change here
    );

    String genericPackageAddress = "com.github.syndexmx.genericspring";
    String targetPackageAddress = "com.github.syndexmx.targetspring"; //change here

    String genericDependencyType = "GenericDependency";
    String targetDependencyType = "TargetDependency"; //change here
    String genericDependencyVariable = "genericDependency";
    String targetDependencyVariable = "targetDependency"; //change here


    String targetClass = StringUtils.capitalize(TARGET_CLASS_NAME);
    String targetClassCamel = StringUtils.uncapitalize(TARGET_CLASS_NAME);
    String targetClassUpper = StringUtils.uncapitalize(TARGET_CLASS_NAME);

    String genericObjectClass = "GenericObject";
    String targetObjectClass = targetClass;
    String genericObjectLowCaseName = "genericObject";
    String targetObjectLowCaseName = targetClassCamel;

    String genericType = "Generic";
    String targetType = targetClass;

    String genericLowCaseName = "generic";
    String targetLowCaseName = targetClassCamel;

    String genericAllUpperCaseName = "GENERIC";
    String targetAllUpperCaseName = targetClassUpper;

    /*

     String genericPackageAddress = "com.github.syndexmx.genericspring";
    String targetPackageAddress = "com.github.syndexmx.targetspring"; //change here

    String genericObjectClass = "GenericObject";
    String targetObjectClass = "Target"; //change here
    String genericObjectLowCaseName = "genericObject";
    String targetObjectLowCaseName = "target"; //change here

    String genericType = "Generic";
    String targetType = "Target"; //change here

    String genericLowCaseName = "generic";
    String targetLowCaseName = "target"; //change here

    String genericIdType = "UUID";
    String targetIdType = "UUID"; //change here

    String genericDependencyType = "GenericDependency";
    String targetDependencyType = "TargetDependency"; //change here
    String genericDependencyVariable = "genericDependency";
    String targetDependencyVariable = "targetDependency"; //change here

    String genericFieldType = "Short";
    String genericFieldName = "genericField";
    Map<String, String> fieldsMap = Map.ofEntries( // Object Fields block
            entry("targetStringVariable", "String"), // Change here
            entry("targetIntegerVariable", "Integer"), // Change here
            entry("targetBooleanVariable", "Boolean") // Change here
    );

    String genericAllUpperCaseName = "GENERIC";
    String targetAllUpperCaseName = "TARGET"; //change

    //@TemplatedAnnotation{
        whole
        multiline
        block
        with generic
    //@TemplatedAnnotation}

     */

}
