package com.github.syndexmx.genericspring.domain;

import java.util.UUID;

public class TestGenericSupplier {

    private static UUID uuid = UUID.randomUUID();

    private static String genericString = "Test String";

    private static String genericStringModified = "Modified Test String";

    public static Generic getTestGeneric( ) {
        return Generic.builder()
                .genericId(uuid)
                .genericString(genericString)
                .build();
    }

    public static Generic getModifiedTestGeneric( ) {
        return Generic.builder()
                .genericId(uuid)
                .genericString(genericString)
                .build();
    }

    private static UUID nonExistentUuid = UUID.randomUUID();

    private static String nonExistentString = "non existent String";

    public static Generic getTestNonExistentGeneric( ) {
        return Generic.builder()
                .genericId(nonExistentUuid)
                .genericString(nonExistentString)
                .build();
    }

}
