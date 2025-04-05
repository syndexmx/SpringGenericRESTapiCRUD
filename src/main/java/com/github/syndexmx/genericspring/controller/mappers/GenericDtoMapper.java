package com.github.syndexmx.genericspring.controller.mappers;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.controller.dtos.GenericDto;
import com.github.syndexmx.genericspring.domain.GenericFields;
import com.github.syndexmx.genericspring.domain.GenericObject;
import org.springframework.stereotype.Component;

import java.util.UUID;

@TemplatedAnnotation
@Component
public class GenericDtoMapper {

    public static GenericDto map(GenericObject genericObject) {
        final GenericDto genericDto = GenericDto.builder()
                .id(genericObject.getId().toString())
                .genericFields(genericObject.getGenericFields().toString())
                .build();
        return genericDto;
    }

    public static GenericObject map(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.fromString(genericDto.getId()))
                .genericFields(GenericFields.valueOf(genericDto.getGenericFields()))
                .build();
        return genericObject;
    }

    public static GenericObject mapWithNoId(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.randomUUID())
                .genericFields(GenericFields.valueOf(genericDto.getGenericFields()))
                .build();
        return genericObject;
    }

}
