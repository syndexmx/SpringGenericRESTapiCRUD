package com.github.syndexmx.genericspring.repository.repositories;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.repository.entities.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@TemplatedAnnotation
@Repository
public interface GenericRepository extends JpaRepository<GenericEntity, UUID> {
}
