package com.github.syndexmx.genericspring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GenericPojo {

    private UUID genericPojoId;

    private String genericString;

}
