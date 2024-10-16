package com.github.syndexmx.genericspring.services;

import com.github.syndexmx.genericspring.domain.Generic;
import org.springframework.stereotype.Service;

@Service
public interface GenericService {

    public Generic create(Generic generic);
}
