package com.github.syndexmx.genericspring.controllers.dtos;

import com.github.syndexmx.genericspring.domain.GenericObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenericDto {

    private String genericId;

    private String genericString;


}
