package com.github.syndexmx.genericspring.controller.controllers;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.controller.mappers.GenericDtoMapper;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.controller.dtos.GenericDto;
import com.github.syndexmx.genericspring.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@RestController
public class GenericController {

    private final String ROOT_API_PATH = "/api/v0/generics";

    private final GenericService genericService;

    @Autowired
    private GenericController(GenericService genericService, GenericDtoMapper genericDtoMapper) {
        this.genericService = genericService;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<GenericDto> create(@RequestBody final GenericDto genericDto) {
        final GenericObject genericObject = GenericDtoMapper.genericDtoNoIdToGeneric(genericDto);
        final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                GenericDtoMapper.genericToGenericDto(genericService.create(genericObject)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{genericId}")
    public ResponseEntity<GenericDto> retrieve(@PathVariable String genericId) {
        final Optional<GenericObject> foundGeneric = genericService.findById(genericId);
        if (foundGeneric.isEmpty()) {
            return new ResponseEntity<GenericDto>(HttpStatus.NOT_FOUND);
        } else {
            final GenericDto genericDto = GenericDtoMapper.genericToGenericDto(foundGeneric.get());
            return new ResponseEntity<GenericDto>(genericDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    public ResponseEntity<List<GenericDto>> retrieveAll() {
        final List<GenericObject> listFound = genericService.listAll();
        final List<GenericDto> listFoundDtos = listFound.stream()
                .map(generic -> GenericDtoMapper.genericToGenericDto(generic)).toList();
        final ResponseEntity<List<GenericDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{genericId}")
    public ResponseEntity<GenericDto> update(@RequestBody final GenericDto genericDto) {
        final GenericObject genericObject = GenericDtoMapper.genericDtoToGeneric(genericDto);
        if (!genericService.isPresent(genericObject)) {
            final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                    GenericDtoMapper.genericToGenericDto(genericService.save(genericObject)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                GenericDtoMapper.genericToGenericDto(genericService.save(genericObject)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{genericId}")
    public ResponseEntity deleteById(@PathVariable String genericId) {
        genericService.deleteById(genericId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
