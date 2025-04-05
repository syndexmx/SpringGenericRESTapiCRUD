package com.github.syndexmx.genericspring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static java.util.Map.entry;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface TemplatedAnnotation {

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

    /*

    String genericPackageAddress = "com.github.syndexmx.genericspring";
    String targetPackageAddress = "com.github.syndexmx.targetspring"; //change here

    String genericObjectClass = "GenericObject";
    String targetObjectClass = "Target"; //change here
    String genericObjectLowCaseName = "genericObject";
    String targetObjectLowCaseName = "target"; //change here

    String genericName = "Generic";
    String targetName = "Target"; //change here

    String genericLowCaseName = "generic";
    String targetLowCaseName = "target"; //change here

    String genericIdType = "UUID";
    String targetIdType = "UUID"; //change here

    String genericDependencyType = "genericDependency";
    String targetDependencyType = "genericDependency"; //change here
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

     */

@TemplatedAnnotation
    class InnerTemplatedAnnotationClass {
    }

}
