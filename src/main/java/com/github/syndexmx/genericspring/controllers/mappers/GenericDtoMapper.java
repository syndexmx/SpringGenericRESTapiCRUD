package com.github.syndexmx.genericspring.controllers.mappers;

import com.github.syndexmx.genericspring.controllers.dtos.GenericDto;
import com.github.syndexmx.genericspring.domain.GenericObject;

public class GenericDtoMapper {

    public static GenericDto genericToGenericDto(GenericObject genericObject) {
        final GenericDto genericDto = GenericDto.builder()
                .genericId(genericObject.getGenericId())
                .genericString(genericObject.getGenericString())
                .build();
        return genericDto;
    }

    public static GenericObject genericDtoToGeneric(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .genericId(genericDto.getGenericId())
                .genericString(genericDto.getGenericString())
                .build();
        return genericObject;
    }

}
