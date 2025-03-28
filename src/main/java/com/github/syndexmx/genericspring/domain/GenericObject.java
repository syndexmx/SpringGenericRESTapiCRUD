package com.github.syndexmx.genericspring.domain;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@TemplatedAnnotation
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GenericObject {

    private UUID id;

    private String genericFieldContent;

}
