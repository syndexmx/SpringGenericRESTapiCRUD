package com.github.syndexmx.genericspring.domain;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;

import java.util.UUID;

@TemplatedAnnotation
public class GenericObjectTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static GenericFields GENERIC_FIELD_VALUE = GenericFields.DEFAULTVALUE;
    private static GenericFields GENERIC_STRING_MODIFIED = GenericFields.ALTERNATIVEVALUE;

    public static GenericObject getTestGeneric( ) {
        return GenericObject.builder()
                .id(id)
                .genericFields(GENERIC_FIELD_VALUE)
                .build();
    }

    public static GenericObject getModifiedTestGeneric( ) {
        return GenericObject.builder()
                .id(id)
                .genericFields(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();
    private static String NON_EXISTANT_STRING = "Non existent String";

    public static GenericObject getTestNonExistentGeneric( ) {
        return GenericObject.builder()
                .id(NON_EXISTENT_UUID)
                .genericFields(GenericFields.OTHERVALUE)
                .build();
    }

}
