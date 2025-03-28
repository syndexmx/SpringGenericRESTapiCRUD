package com.github.syndexmx.genericspring.repositories.mappers;

import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.repositories.entities.GenericEntity;

public class GenericEntityMapper {

    public static GenericEntity genericToGenericEntity(GenericObject genericObject) {
        final GenericEntity genericEntity = GenericEntity.builder()
                .genericId(genericObject.getId())
                .genericString(genericObject.getGenericString())
                .build();
        return genericEntity;
    }

    public static GenericObject genericEntityToGeneric(GenericEntity genericEntity) {
        GenericObject genericObject = GenericObject.builder()
                .id(genericEntity.getGenericId())
                .genericString(genericEntity.getGenericString())
                .build();
        return genericObject;

    }

}
