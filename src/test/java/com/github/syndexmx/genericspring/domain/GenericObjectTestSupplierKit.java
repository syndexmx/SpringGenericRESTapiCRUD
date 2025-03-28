package com.github.syndexmx.genericspring.domain;

import java.util.UUID;

public class GenericObjectTestSupplierKit {

    private static UUID id = UUID.randomUUID();

    private static String GENERIC_STRING = "Test String";
    private static String GENERIC_STRING_MODIFIED = "Modified Test String";

    public static GenericObject getTestGeneric( ) {
        return GenericObject.builder()
                .id(id)
                .genericFieldContent(GENERIC_STRING)
                .build();
    }

    public static GenericObject getModifiedTestGeneric( ) {
        return GenericObject.builder()
                .id(id)
                .genericFieldContent(GENERIC_STRING_MODIFIED)
                .build();
    }

    private static UUID NON_EXISTENT_UUID = UUID.randomUUID();
    private static String NON_EXISTANT_STRING = "Non existent String";

    public static GenericObject getTestNonExistentGeneric( ) {
        return GenericObject.builder()
                .id(NON_EXISTENT_UUID)
                .genericFieldContent(NON_EXISTANT_STRING)
                .build();
    }

}
