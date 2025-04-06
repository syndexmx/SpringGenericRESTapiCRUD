package com.github.syndexmx.genericspring.repository.mappers;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.domain.GenericDependency;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.repository.entities.GenericEntity;
import org.springframework.stereotype.Component;

import java.nio.channels.MulticastChannel;

@TemplatedAnnotation
@Component
public class GenericEntityMapper {

    public static GenericEntity map(GenericObject genericObject) {
        final GenericEntity genericEntity = GenericEntity.builder()
                .genericId(genericObject.getId())
                .genericField(genericObject.getGenericField())
                .genericDependency(genericObject.getGenericDependency().toString())
                .build();
        return genericEntity;
    }

    public static GenericObject map(GenericEntity genericEntity) {
        GenericObject genericObject = GenericObject.builder()
                .id(genericEntity.getGenericId())
                .genericField(genericEntity.getGenericField())
                .genericDependency(GenericDependency.valueOf(genericEntity.getGenericDependency()))
                .build();
        return genericObject;
    }

}
