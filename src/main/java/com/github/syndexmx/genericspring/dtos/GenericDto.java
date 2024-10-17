package com.github.syndexmx.genericspring.dtos;

import com.github.syndexmx.genericspring.annotations.CopyCatClass;
import com.github.syndexmx.genericspring.annotations.CopyCatOperation;
import com.github.syndexmx.genericspring.domain.Generic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@CopyCatClass
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenericDto {

    private String genericId;

    private String genericString;

    @CopyCatOperation
    public static GenericDto genericToGenericDto(Generic generic) {
        final GenericDto genericDto = GenericDto.builder()
                .genericId(generic.getGenericId())
                .genericString(generic.getGenericString())
                .build();
        return genericDto;
    }

    @CopyCatOperation
    public static Generic genericDtoToGeneric(GenericDto genericDto) {
        Generic generic = Generic.builder()
                .genericId(genericDto.getGenericId())
                .genericString(genericDto.getGenericString())
                .build();
        return generic;
    }
}
