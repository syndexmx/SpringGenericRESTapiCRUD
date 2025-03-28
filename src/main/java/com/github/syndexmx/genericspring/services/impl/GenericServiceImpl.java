package com.github.syndexmx.genericspring.services.impl;

import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.repositories.entities.GenericEntity;
import com.github.syndexmx.genericspring.repositories.GenericRepository;
import com.github.syndexmx.genericspring.services.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.syndexmx.genericspring.repositories.mappers.GenericEntityMapper.genericEntityToGeneric;
import static com.github.syndexmx.genericspring.repositories.mappers.GenericEntityMapper.genericToGenericEntity;

@Service
@Slf4j
public class GenericServiceImpl implements GenericService {

    private final GenericRepository genericRepository;

    @Autowired
    private GenericServiceImpl(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public GenericObject create(GenericObject genericObject) {
        String spoofId;
        do {
            spoofId = UUID.randomUUID().toString();
        } while (genericRepository.existsById(UUID.fromString(spoofId)));
        genericObject.setGenericId(spoofId);
        final GenericEntity savedEntity = genericRepository.save(genericToGenericEntity(genericObject));
        final GenericObject savedGenericObject = genericEntityToGeneric(savedEntity);
        return savedGenericObject;
    }

    @Override
    public GenericObject save(GenericObject genericObject) {
        final GenericEntity savedEntity = genericRepository.save(genericToGenericEntity(genericObject));
        final GenericObject savedGenericObject = genericEntityToGeneric(savedEntity);
        return savedGenericObject;
    }

    @Override
    public Optional<GenericObject> findById(String genericId) {
        final Optional<GenericEntity> genericEntityFound = genericRepository
                .findById(UUID.fromString(genericId));
        final Optional<GenericObject> genericFound = genericEntityFound.map(genericEntity ->
                genericEntityToGeneric(genericEntity));
        return genericFound;
    }

    @Override
    public List<GenericObject> listGenerics() {
        final List<GenericEntity> listOfFoundGenericEntities = genericRepository.findAll();
        final List<GenericObject> listOfFoundGenericObjects =listOfFoundGenericEntities.stream()
                .map(entity -> genericEntityToGeneric(entity)).toList();
        return listOfFoundGenericObjects;
    }

    @Override
    public boolean isPresent(String genericId) {
        return genericRepository.existsById(UUID.fromString(genericId));
    }

    @Override
    public boolean isPresent(GenericObject genericObject) {
        return genericRepository.existsById(UUID.fromString(genericObject.getGenericId()));
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
