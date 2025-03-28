package com.github.syndexmx.genericspring.domain;

import java.util.UUID;

public class TestGenericSupplier {

    private static String id = UUID.randomUUID().toString();

    private static String genericString = "Test String";

    private static String genericStringModified = "Modified Test String";

    public static GenericObject getTestGeneric( ) {
        return GenericObject.builder()
                .id(id)
                .genericString(genericString)
                .build();
    }

    public static GenericObject getModifiedTestGeneric( ) {
        return GenericObject.builder()
                .id(id)
                .genericString(genericString)
                .build();
    }

    private static String nonExistentUuid = UUID.randomUUID().toString();

    private static String nonExistentString = "non existent String";

    public static GenericObject getTestNonExistentGeneric( ) {
        return GenericObject.builder()
                .id(nonExistentUuid)
                .genericString(nonExistentString)
                .build();
    }

}
