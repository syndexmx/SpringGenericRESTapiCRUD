package com.github.syndexmx.genericspring.dtos;

import com.github.syndexmx.genericspring.domain.GenericPojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenericDto {

    private UUID genericId;

    private String genericString;

    public static GenericDto genericPojoToGenericEntity(GenericPojo genericPojo) {
        final GenericDto genericDto = GenericDto.builder()
                .genericId(genericPojo.getGenericId())
                .genericString(genericPojo.getGenericString())
                .build();
        return genericDto;
    }

    public static GenericPojo genericPojo(GenericDto genericDto) {
        GenericPojo genericPojo = GenericPojo.builder()
                .genericId(genericDto.getGenericId())
                .genericString(genericDto.getGenericString())
                .build();
        return genericPojo;
    }
}
