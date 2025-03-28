package com.github.syndexmx.genericspring.controllers.mappers;

import com.github.syndexmx.genericspring.controllers.dtos.GenericDto;
import com.github.syndexmx.genericspring.domain.GenericObject;

import java.util.UUID;

public class GenericDtoMapper {

    public static GenericDto genericToGenericDto(GenericObject genericObject) {
        final GenericDto genericDto = GenericDto.builder()
                .id(genericObject.getId().toString())
                .genericString(genericObject.getGenericString())
                .build();
        return genericDto;
    }

    public static GenericObject genericDtoToGeneric(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.fromString(genericDto.getId()))
                .genericString(genericDto.getGenericString())
                .build();
        return genericObject;
    }

    public static GenericObject genericDtoNoIdToGeneric(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.randomUUID())
                .genericString(genericDto.getGenericString())
                .build();
        return genericObject;
    }

}
