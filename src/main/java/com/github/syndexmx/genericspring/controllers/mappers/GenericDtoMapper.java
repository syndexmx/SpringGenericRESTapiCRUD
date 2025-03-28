package com.github.syndexmx.genericspring.controllers.mappers;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.controllers.dtos.GenericDto;
import com.github.syndexmx.genericspring.domain.GenericObject;

import java.util.UUID;

@TemplatedAnnotation
public class GenericDtoMapper {

    public static GenericDto genericToGenericDto(GenericObject genericObject) {
        final GenericDto genericDto = GenericDto.builder()
                .id(genericObject.getId().toString())
                .genericFieldContent(genericObject.getGenericFieldContent())
                .build();
        return genericDto;
    }

    public static GenericObject genericDtoToGeneric(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.fromString(genericDto.getId()))
                .genericFieldContent(genericDto.getGenericFieldContent())
                .build();
        return genericObject;
    }

    public static GenericObject genericDtoNoIdToGeneric(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.randomUUID())
                .genericFieldContent(genericDto.getGenericFieldContent())
                .build();
        return genericObject;
    }

}
