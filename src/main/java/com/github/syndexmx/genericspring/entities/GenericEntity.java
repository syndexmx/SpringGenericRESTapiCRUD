package com.github.syndexmx.genericspring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "generics_table")
public class GenericEntity {

    @Id
    @Column(name = "generic_id")
    private UUID genericPojoId;

    @Column(name = "generic_string")
    private String genericString;

}
