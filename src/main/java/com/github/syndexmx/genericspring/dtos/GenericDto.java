package com.github.syndexmx.genericspring.dtos;

import com.github.syndexmx.genericspring.domain.Generic;
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

    public static GenericDto genericToGenericDto(Generic generic) {
        final GenericDto genericDto = GenericDto.builder()
                .genericId(generic.getGenericId())
                .genericString(generic.getGenericString())
                .build();
        return genericDto;
    }

    public static Generic genericDtoToGeneric(GenericDto genericDto) {
        Generic generic = Generic.builder()
                .genericId(genericDto.getGenericId())
                .genericString(genericDto.getGenericString())
                .build();
        return generic;
    }
}
