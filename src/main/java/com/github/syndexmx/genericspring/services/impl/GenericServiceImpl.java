package com.github.syndexmx.genericspring.services.impl;

import com.github.syndexmx.genericspring.domain.Generic;
import com.github.syndexmx.genericspring.entities.GenericEntity;
import com.github.syndexmx.genericspring.repositories.GenericRepository;
import com.github.syndexmx.genericspring.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.github.syndexmx.genericspring.entities.GenericEntity.genericEntityToGeneric;
import static com.github.syndexmx.genericspring.entities.GenericEntity.genericToGenericEntity;

@Service
public class GenericServiceImpl implements GenericService {

    private final GenericRepository genericRepository;

    @Autowired
    private GenericServiceImpl(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public Generic create(Generic generic) {
        final GenericEntity savedEntity = genericRepository.save(genericToGenericEntity(generic));
        final Generic savedGeneric = genericEntityToGeneric(savedEntity);
        return savedGeneric;
    }

    @Override
    public Optional<Generic> findById(UUID genericUuid) {
        final Optional<GenericEntity> genericEntityFound = genericRepository.findById(genericUuid);
        final Optional<Generic> genericFound = genericEntityFound.map(genericEntity ->
                genericEntityToGeneric(genericEntity));
        return genericFound;
    }

}
