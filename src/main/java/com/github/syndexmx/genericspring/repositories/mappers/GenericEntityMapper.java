package com.github.syndexmx.genericspring.repositories.mappers;

import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.repositories.entities.GenericEntity;

import java.util.UUID;

public class GenericEntityMapper {

    public static GenericEntity genericToGenericEntity(GenericObject genericObject) {
        final GenericEntity genericEntity = GenericEntity.builder()
                .genericId(UUID.fromString(genericObject.getGenericId()))
                .genericString(genericObject.getGenericString())
                .build();
        return genericEntity;
    }

    public static GenericObject genericEntityToGeneric(GenericEntity genericEntity) {
        GenericObject genericObject = GenericObject.builder()
                .genericId(genericEntity.getGenericId().toString())
                .genericString(genericEntity.getGenericString())
                .build();
        return genericObject;

    }

}
