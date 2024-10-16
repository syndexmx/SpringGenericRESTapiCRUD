package com.github.syndexmx.genericspring.services.impl;

import com.github.syndexmx.genericspring.domain.Generic;
import com.github.syndexmx.genericspring.entities.GenericEntity;
import com.github.syndexmx.genericspring.repositories.GenericRepository;
import com.github.syndexmx.genericspring.services.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.syndexmx.genericspring.entities.GenericEntity.genericEntityToGeneric;
import static com.github.syndexmx.genericspring.entities.GenericEntity.genericToGenericEntity;

@Service
@Slf4j
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
    public Generic save(Generic generic) {
        final GenericEntity savedEntity = genericRepository.save(genericToGenericEntity(generic));
        final Generic savedGeneric = genericEntityToGeneric(savedEntity);
        return savedGeneric;
    }

    @Override
    public Optional<Generic> findById(String genericId) {
        final Optional<GenericEntity> genericEntityFound = genericRepository
                .findById(UUID.fromString(genericId));
        final Optional<Generic> genericFound = genericEntityFound.map(genericEntity ->
                genericEntityToGeneric(genericEntity));
        return genericFound;
    }

    @Override
    public List<Generic> listGenerics() {
        final List<GenericEntity> listOfFoundGenericEntities = genericRepository.findAll();
        final List<Generic> listOfFoundGenerics =listOfFoundGenericEntities.stream()
                .map(entity -> genericEntityToGeneric(entity)).toList();
        return listOfFoundGenerics;
    }

    @Override
    public boolean isPresent(String genericId) {
        return genericRepository.existsById(UUID.fromString(genericId));
    }

    @Override
    public boolean isPresent(Generic generic) {
        return genericRepository.existsById(UUID.fromString(generic.getGenericId()));
    }

    @Override
    public void deleteGenericById(String genericId) {
        try {
            genericRepository.deleteById(UUID.fromString(genericId));
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent generic");
        }
    }

}
