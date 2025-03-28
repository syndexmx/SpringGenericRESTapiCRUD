package com.github.syndexmx.genericspring.services;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.domain.GenericObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@Service
public interface GenericService {

    GenericObject create(GenericObject genericObject);

    GenericObject save(GenericObject genericObject);

    Optional<GenericObject> findById(String genericId);

    List<GenericObject> listAll();

    boolean isPresent(String genericId);

    boolean isPresent(GenericObject genericObject);

    void deleteById(String genericId);

}
