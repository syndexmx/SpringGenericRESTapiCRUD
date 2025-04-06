package com.github.syndexmx.genericspring.repository.entities;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.domain.GenericDependency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@TemplatedAnnotation
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "generics")
public class GenericEntity {

    @Id
    private UUID genericId;
    private Short genericField;
    private String genericDependency;
    private List<String> genericDependencyList;

}
