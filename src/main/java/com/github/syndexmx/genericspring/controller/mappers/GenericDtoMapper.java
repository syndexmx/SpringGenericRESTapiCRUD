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

    public GenericDto genericToGenericDto(GenericObject genericObject) {
        final GenericDto genericDto = GenericDto.builder()
                .id(genericObject.getId().toString())
                .genericFieldContent(genericObject.getGenericFields().toString())
                .build();
        return genericDto;
    }

    public GenericObject genericDtoToGeneric(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.fromString(genericDto.getId()))
                .genericFields(GenericFields.valueOf(genericDto.getGenericFieldContent()))
                .build();
        return genericObject;
    }

    public GenericObject genericDtoNoIdToGeneric(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.randomUUID())
                .genericFields(GenericFields.valueOf(genericDto.getGenericFieldContent()))
                .build();
        return genericObject;
    }

}
