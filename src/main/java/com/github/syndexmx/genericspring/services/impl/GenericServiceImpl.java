package com.github.syndexmx.genericspring.services.impl;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.repository.entities.GenericEntity;
import com.github.syndexmx.genericspring.repository.mappers.GenericEntityMapper;
import com.github.syndexmx.genericspring.repository.repositories.GenericRepository;
import com.github.syndexmx.genericspring.services.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@TemplatedAnnotation
@Service
@Slf4j
public class GenericServiceImpl implements GenericService {

    private final GenericRepository genericRepository;

    @Autowired
    private GenericServiceImpl(GenericRepository genericRepository, GenericEntityMapper genericEntityMapper) {
        this.genericRepository = genericRepository;
    }

    @Override
    public GenericObject create(GenericObject genericObject) {
        UUID spoofId = UUID.randomUUID();
        genericObject.setId(spoofId);
        final GenericEntity savedEntity = genericRepository.save(GenericEntityMapper
                .map(genericObject));
        final GenericObject savedGenericObject = GenericEntityMapper.map(savedEntity);
        return savedGenericObject;
    }

    @Override
    public GenericObject save(GenericObject genericObject) {
        final GenericEntity savedEntity = genericRepository.save(GenericEntityMapper
                .map(genericObject));
        final GenericObject savedGenericObject = GenericEntityMapper.map(savedEntity);
        return savedGenericObject;
    }

    @Override
    public Optional<GenericObject> findById(UUID genericId) {
        final Optional<GenericEntity> genericEntityFound = genericRepository
                .findById(genericId);
        final Optional<GenericObject> genericFound = genericEntityFound.map(genericEntity ->
                GenericEntityMapper.map(genericEntity));
        return genericFound;
    }

    @Override
    public List<GenericObject> listAll() {
        final List<GenericEntity> listOfFoundGenericEntities = genericRepository.findAll();
        final List<GenericObject> listOfFoundGenericObjects =listOfFoundGenericEntities.stream()
                .map(entity -> GenericEntityMapper.map(entity)).toList();
        return listOfFoundGenericObjects;
    }

    @Override
    public boolean isPresent(UUID genericId) {
        return genericRepository.existsById(genericId);
    }

    @Override
    public boolean isPresent(GenericObject genericObject) {
        return genericRepository.existsById(genericObject.getId());
    }

    @Override
    public void deleteById(UUID genericId) {
        try {
            genericRepository.deleteById(genericId);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existent generic");
        }
    }

}
