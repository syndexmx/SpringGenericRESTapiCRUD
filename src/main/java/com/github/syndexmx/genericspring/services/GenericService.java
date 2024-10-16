package com.github.syndexmx.genericspring.services;

import com.github.syndexmx.genericspring.domain.Generic;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface GenericService {

    public Generic create(Generic generic);

    Optional<Generic> findById(UUID genericUuid);
}
