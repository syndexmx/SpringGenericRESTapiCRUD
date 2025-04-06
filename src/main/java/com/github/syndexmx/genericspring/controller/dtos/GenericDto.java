package com.github.syndexmx.genericspring.controller.dtos;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@TemplatedAnnotation
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenericDto {

    private String id;
    private Short genericField;
    private String genericDependency;
    private List<String> genericDependencyList;

}
