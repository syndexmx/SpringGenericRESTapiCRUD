package com.github.syndexmx.genericspring.entities;

import com.github.syndexmx.genericspring.domain.GenericPojo;
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

    public GenericEntity genericPojoToGenericEntity(GenericPojo genericPojo) {
        final GenericEntity genericEntity = GenericEntity.builder()
                .genericId(genericPojo.getGenericId())
                .genericString(genericPojo.getGenericString())
                .build();
        return genericEntity;
    }

    public GenericPojo genericPojo(GenericEntity genericEntity) {
        GenericPojo genericPojo = GenericPojo.builder()
                .genericId(genericEntity.getGenericId())
                .genericString(genericEntity.getGenericString())
                .build();
        return genericPojo;

    }

}
