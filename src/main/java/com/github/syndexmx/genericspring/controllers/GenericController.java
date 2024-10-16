package com.github.syndexmx.genericspring.controllers;

import com.github.syndexmx.genericspring.domain.Generic;
import com.github.syndexmx.genericspring.dtos.GenericDto;
import com.github.syndexmx.genericspring.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static com.github.syndexmx.genericspring.dtos.GenericDto.genericToGenericDto;

@RestController
public class GenericController {

    private final GenericService genericService;

    @Autowired
    private GenericController(GenericService genericService) {
        this.genericService = genericService;
    }

    @PostMapping("/api/v0/generics")
    public ResponseEntity<GenericDto> createEntity(@RequestBody final Generic generic) {
        final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                genericToGenericDto(genericService.create(generic)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/api/v0/generics/{genericId}")
    public ResponseEntity<GenericDto> retrieveGeneric(@PathVariable UUID genericId) {
        final Optional<Generic> foundGeneric = genericService.findById(genericId);
        if (foundGeneric.isEmpty()) {
            return new ResponseEntity<GenericDto>(HttpStatus.NOT_FOUND);
        } else {
            final GenericDto genericDto = genericToGenericDto(foundGeneric.get());
            return new ResponseEntity<GenericDto>(genericDto, HttpStatus.FOUND);
        }
    }

}
