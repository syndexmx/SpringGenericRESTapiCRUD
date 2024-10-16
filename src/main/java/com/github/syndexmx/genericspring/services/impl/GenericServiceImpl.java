package com.github.syndexmx.genericspring.services.impl;

import com.github.syndexmx.genericspring.repositories.GenericRepository;
import com.github.syndexmx.genericspring.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericServiceImpl implements GenericService {

    private final GenericRepository genericRepository;

    @Autowired
    private GenericServiceImpl(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }


}
