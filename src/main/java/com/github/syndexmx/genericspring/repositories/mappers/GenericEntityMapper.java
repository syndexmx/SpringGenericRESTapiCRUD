package com.github.syndexmx.genericspring.repositories.mappers;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.domain.GenericFields;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.repositories.entities.GenericEntity;

@TemplatedAnnotation
public class GenericEntityMapper {

    public static GenericEntity genericToGenericEntity(GenericObject genericObject) {
        final GenericEntity genericEntity = GenericEntity.builder()
                .genericId(genericObject.getId())
                .genericFieldContent(genericObject.getGenericFields().toString())
                .build();
        return genericEntity;
    }

    public static GenericObject genericEntityToGeneric(GenericEntity genericEntity) {
        GenericObject genericObject = GenericObject.builder()
                .id(genericEntity.getGenericId())
                .genericFields(GenericFields.valueOf(genericEntity.getGenericFieldContent()))
                .build();
        return genericObject;
    }



}
