package com.github.syndexmx.genericspring.repositories;

import com.github.syndexmx.genericspring.repositories.entities.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface GenericRepository extends JpaRepository<GenericEntity, UUID> {
}
