package com.github.syndexmx.genericspring.domain;

import com.github.syndexmx.genericspring.annotations.CopyCatClass;

import java.util.UUID;

@CopyCatClass
public class TestGenericSupplier {

    private static String id = UUID.randomUUID().toString();

    private static String genericString = "Test String";

    private static String genericStringModified = "Modified Test String";

    public static Generic getTestGeneric( ) {
        return Generic.builder()
                .genericId(id)
                .genericString(genericString)
                .build();
    }

    public static Generic getModifiedTestGeneric( ) {
        return Generic.builder()
                .genericId(id)
                .genericString(genericString)
                .build();
    }

    private static String nonExistentUuid = UUID.randomUUID().toString();

    private static String nonExistentString = "non existent String";

    public static Generic getTestNonExistentGeneric( ) {
        return Generic.builder()
                .genericId(nonExistentUuid)
                .genericString(nonExistentString)
                .build();
    }

}
