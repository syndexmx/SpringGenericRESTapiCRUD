package com.github.syndexmx.genericspring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface TemplatedAnnotation {

    String genericPackageAddress = "com.github.syndexmx.genericspring";
    String targetPackageAddress = "com.github.syndexmx.genericspring";

    String genericObjectClass = "GenericObject";
    String targetObjectClass = "Target";
    String genericObjectLowCaseName = "genericObject";
    String targetObjectLowCaseName = "targetName";

    String genericName = "Generic";
    String targetName = "Target";
    String genericLowCaseName = "generic";
    String targetLowCaseName = "target";

    String genericIdType = "UUID";
    String targetIdType = "UUID";

    String genericFieldTypes = "GenericFields";
    String genericFieldNames = "genericFields";
    // Fields in Target Class %
    String anyTargetFieldName = "";
    // Fields end %
}
