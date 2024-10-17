package com.github.syndexmx.genericspring.repositories;

import com.github.syndexmx.genericspring.annotations.CopyCatClass;
import com.github.syndexmx.genericspring.entities.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@CopyCatClass
@Repository
public interface GenericRepository extends JpaRepository<GenericEntity, UUID> {
}
