package com.github.syndexmx.genericspring.controller.mappers;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.controller.dtos.GenericDto;
import com.github.syndexmx.genericspring.domain.GenericDependency;
import com.github.syndexmx.genericspring.domain.GenericObject;
import org.springframework.stereotype.Component;

import java.util.UUID;

@TemplatedAnnotation
@Component
public class GenericDtoMapper {

    public static GenericDto map(GenericObject genericObject) {
        final GenericDto genericDto = GenericDto.builder()
                .id(genericObject.getId().toString())
                .genericField(genericObject.getGenericField())
                .genericDependency(genericObject.getGenericDependency().toString())
                //@TemplatedAnnotation{
                .genericDependencyList(genericObject.getGenericDependencyList().stream()
                        .map(genericDependency ->
                                genericDependency.toString()) // genericDependency Mapping
                        .toList())
                //@TemplatedAnnotation}
                .build();
        return genericDto;
    }

    public static GenericObject map(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.fromString(genericDto.getId()))
                .genericField(genericDto.getGenericField())
                .genericDependency(GenericDependency.valueOf(genericDto.getGenericDependency()))
                //@TemplatedAnnotation{
                .genericDependencyList(genericDto.getGenericDependencyList().stream()
                        .map(genericDependency ->
                                GenericDependency.valueOf(genericDependency)) // genericDependency Mapping
                        .toList())
                //@TemplatedAnnotation}
                .build();
        return genericObject;
    }

    public static GenericObject mapWithNoId(GenericDto genericDto) {
        GenericObject genericObject = GenericObject.builder()
                .id(UUID.randomUUID())
                .genericField(genericDto.getGenericField())
                .genericDependency(GenericDependency.valueOf(genericDto.getGenericDependency()))
                //@TemplatedAnnotation{
                .genericDependencyList(genericDto.getGenericDependencyList().stream()
                        .map(genericDependency ->
                                GenericDependency.valueOf(genericDependency)) // genericDependency Mapping
                        .toList())
                //@TemplatedAnnotation}
                .build();
        return genericObject;
    }

}
