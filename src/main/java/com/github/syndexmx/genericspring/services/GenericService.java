package com.github.syndexmx.genericspring.services;

import com.github.syndexmx.genericspring.domain.GenericPojo;
import org.springframework.stereotype.Service;

@Service
public interface GenericService {

    public GenericPojo create(GenericPojo genericPojo);
}
