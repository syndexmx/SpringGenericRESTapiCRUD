package com.github.syndexmx.genericspring.repositories.entities;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String genericFieldContent;


}
