package com.github.syndexmx.genericspring.entities;

import com.github.syndexmx.genericspring.domain.Generic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "generics_table")
public class GenericEntity {

    @Id
    @Column(name = "generic_id")
    private UUID genericId;

    @Column(name = "generic_string")
    private String genericString;

    public static GenericEntity genericToGenericEntity(Generic generic) {
        final GenericEntity genericEntity = GenericEntity.builder()
                .genericId(generic.getGenericId())
                .genericString(generic.getGenericString())
                .build();
        return genericEntity;
    }

    public static Generic genericEntityToGeneric(GenericEntity genericEntity) {
        Generic generic = Generic.builder()
                .genericId(genericEntity.getGenericId())
                .genericString(genericEntity.getGenericString())
                .build();
        return generic;

    }

}
