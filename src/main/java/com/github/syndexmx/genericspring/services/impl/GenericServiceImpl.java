package com.github.syndexmx.genericspring.services.impl;

import com.github.syndexmx.genericspring.domain.GenericPojo;
import com.github.syndexmx.genericspring.entities.GenericEntity;
import com.github.syndexmx.genericspring.repositories.GenericRepository;
import com.github.syndexmx.genericspring.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.syndexmx.genericspring.entities.GenericEntity.genericEntityToGenericPojo;
import static com.github.syndexmx.genericspring.entities.GenericEntity.genericPojoToGenericEntity;

@Service
public class GenericServiceImpl implements GenericService {

    private final GenericRepository genericRepository;

    @Autowired
    private GenericServiceImpl(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public GenericPojo create(GenericPojo genericPojo) {
        final GenericEntity savedEntity = genericRepository.save(genericPojoToGenericEntity(genericPojo));
        final GenericPojo savedPojo = genericEntityToGenericPojo(savedEntity);
        return savedPojo;
    }

}
