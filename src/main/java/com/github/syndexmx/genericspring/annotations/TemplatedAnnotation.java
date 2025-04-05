package com.github.syndexmx.genericspring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface TemplatedAnnotation {

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

    String genericFieldTypes = "GenericFields";
    String targetFieldTypes = "TargetFields"; //change here
    String genericFieldNames = "genericFields";
    String targetFieldNames = "targetFields"; //change here

    String genericAllUpperCaseName = "GENERIC";
    String targetAllUpperCaseName = "TARGET"; //change

    /*

    String genericPackageAddress = "com.github.syndexmx.genericspring";
    String targetPackageAddress = "com.github.syndexmx.targetspringproject"; //change here

    String genericObjectClass = "GenericObject";
    String targetObjectClass = "Target"; //change here
    String genericObjectLowCaseName = "genericObject";
    String targetObjectLowCaseName = "targetName"; //change here

    String genericName = "Generic";
    String targetName = "Target"; //change here

    String genericLowCaseName = "generic";
    String targetLowCaseName = "target"; //change here

    String genericIdType = "UUID";
    String targetIdType = "UUID"; //change here

    String genericFieldTypes = "GenericFields";
    String targetFieldTypes = "TargetFields"; //change here
    String genericFieldNames = "genericFields";
    String targetFieldNames = "targetFields"; //change here

    String genericAllUpperCaseName = "GENERIC";
    String targetAllUpperCaseName = "TARGET"; //change

     */

@TemplatedAnnotation
    class InnerTemplatedAnnotationClass {
    }

}
