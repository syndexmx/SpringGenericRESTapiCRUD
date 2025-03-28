package com.github.syndexmx.genericspring.repositories.mappers;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.repositories.entities.GenericEntity;

@TemplatedAnnotation
public class GenericEntityMapper {

    public static GenericEntity genericToGenericEntity(GenericObject genericObject) {
        final GenericEntity genericEntity = GenericEntity.builder()
                .genericId(genericObject.getId())
                .genericFieldContent(genericObject.getGenericFieldContent())
                .build();
        return genericEntity;
    }

    public static GenericObject genericEntityToGeneric(GenericEntity genericEntity) {
        GenericObject genericObject = GenericObject.builder()
                .id(genericEntity.getGenericId())
                .genericFieldContent(genericEntity.getGenericFieldContent())
                .build();
        return genericObject;

    }

}
