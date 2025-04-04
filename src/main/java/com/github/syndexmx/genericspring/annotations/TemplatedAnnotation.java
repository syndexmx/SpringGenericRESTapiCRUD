package com.github.syndexmx.genericspring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface TemplatedAnnotation {

    String genericPackageAddress = "com.github.syndexmx.genericspring";
    String targetPackageAddress = "com.github.syndexmx.demoairfleet"; //change here

    String genericObjectClass = "GenericObject";
    String targetObjectClass = "Flight"; //change here
    String genericObjectLowCaseName = "genericObject";
    String targetObjectLowCaseName = "flight"; //change here

    String genericName = "Generic";
    String targetName = "Flight"; //change here

    String genericLowCaseName = "generic";
    String targetLowCaseName = "flight"; //change here

    String genericIdType = "UUID";
    String targetIdType = "UUID"; //change here

    String genericFieldTypes = "GenericFields";
    String targetFieldTypes = "FlightData"; //change here
    String genericFieldNames = "genericFields";
    String targetFieldNames = "flightData"; //change here

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
     */

@TemplatedAnnotation
    class InnerTemplatedAnnotationClass {
    }

}
