package com.github.syndexmx.genericspring.services;

import com.github.syndexmx.genericspring.annotations.CopyCatClass;
import com.github.syndexmx.genericspring.domain.Generic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CopyCatClass
@Service
public interface GenericService {

    Generic create(Generic generic);

    Generic save(Generic generic);

    Optional<Generic> findById(String genericId);

    List<Generic> listGenerics();

    boolean isPresent(String genericId);

    boolean isPresent(Generic generic);

    void deleteGenericById(String genericId);

}
