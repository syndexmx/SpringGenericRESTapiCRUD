package com.github.syndexmx.genericspring.repositories.entities;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "generics_table")
public class GenericEntity {

    @Id
    private UUID genericId;

    private String genericFieldContent;


}
