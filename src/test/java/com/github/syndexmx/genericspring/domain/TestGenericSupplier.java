package com.github.syndexmx.genericspring.domain;

import java.util.UUID;

public class TestGenericSupplier {

    private static UUID uuid = UUID.randomUUID();

    private static String genericString = "Test String";

    public static Generic getTestGeneric( ) {
        return Generic.builder()
                .genericId(uuid)
                .genericString(genericString)
                .build();
    }

}
