package com.github.syndexmx.genericspring.controllers;

import com.github.syndexmx.genericspring.domain.Generic;
import com.github.syndexmx.genericspring.dtos.GenericDto;
import com.github.syndexmx.genericspring.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.syndexmx.genericspring.dtos.GenericDto.genericDtoToGeneric;
import static com.github.syndexmx.genericspring.dtos.GenericDto.genericToGenericDto;

@RestController
public class GenericController {

    private final GenericService genericService;

    @Autowired
    private GenericController(GenericService genericService) {
        this.genericService = genericService;
    }

    @PostMapping("/api/v0/generics")
    public ResponseEntity<GenericDto> createEntity(@RequestBody final GenericDto genericDto) {
        final Generic generic = genericDtoToGeneric(genericDto);
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

    @GetMapping("/api/v0/generics")
    public ResponseEntity<List<GenericDto>> retrieveAllGenerics() {
        final List<Generic> listFoundGenerics = genericService.listGenerics();
        final List<GenericDto> listFoundGenericDtos = listFoundGenerics.stream()
                .map(generic -> genericToGenericDto(generic)).toList();
        final ResponseEntity<List<GenericDto>> response = new ResponseEntity<>(listFoundGenericDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping("/api/v0/generics")
    public ResponseEntity<GenericDto> updateEntity(@RequestBody final GenericDto genericDto) {
        final Generic generic = genericDtoToGeneric(genericDto);
        if (!genericService.isPresent(generic)) {
            final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                    genericToGenericDto(genericService.save(generic)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                genericToGenericDto(genericService.save(generic)), HttpStatus.OK);
        return responseEntity;
    }

}
