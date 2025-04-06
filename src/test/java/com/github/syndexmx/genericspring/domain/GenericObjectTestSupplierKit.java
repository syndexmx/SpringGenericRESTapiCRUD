package com.github.syndexmx.genericspring.domain;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;

import java.util.List;
import java.util.UUID;

@TemplatedAnnotation
public class GenericObjectTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static GenericDependency GENERIC_FIELD_VALUE = GenericDependency.DEFAULTVALUE;
    private static GenericDependency GENERIC_STRING_MODIFIED = GenericDependency.ALTERNATIVEVALUE;

    public static GenericObject getTestGeneric() {
        return GenericObject.builder()
                .id(id)
                .genericDependencyList(List.of())
                .genericDependency(GENERIC_FIELD_VALUE)
                .build();
    }

    public static GenericObject getModifiedTestGeneric() {
        return GenericObject.builder()
                .id(id)
                .genericDependency(GENERIC_STRING_MODIFIED)
                .genericDependencyList(List.of())
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();
    private static GenericDependency NON_EXISTANT_VALUE = GenericDependency.OTHERVALUE;

    public static GenericObject getTestNonExistentGeneric( ) {
        return GenericObject.builder()
                .id(NON_EXISTENT_UUID)
                .genericDependency(NON_EXISTANT_VALUE)
                .genericDependencyList(List.of())
                .build();
    }

}
