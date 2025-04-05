package com.github.syndexmx.genericspring.controller.controllers;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.controller.mappers.GenericDtoMapper;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.controller.dtos.GenericDto;
import com.github.syndexmx.genericspring.services.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@TemplatedAnnotation
@RestController
@Slf4j
public class GenericController {

    private final String ROOT_API_PATH = "/api/v0/generics";

    private final GenericService genericService;

    @Autowired
    private GenericController(GenericService genericService, GenericDtoMapper genericDtoMapper) {
        this.genericService = genericService;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<GenericDto> create(@RequestBody final GenericDto genericDto) {
        log.info("PUT @ " + ROOT_API_PATH + " : " + genericDto.toString());
        final GenericObject genericObject = GenericDtoMapper.mapWithNoId(genericDto);
        final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                GenericDtoMapper.map(genericService.create(genericObject)), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(ROOT_API_PATH +"/{genericId}")
    public ResponseEntity<GenericDto> retrieve(@PathVariable String genericId) {
        log.debug("GET @ " + ROOT_API_PATH + " : " + genericId.toString());
        final Optional<GenericObject> foundGeneric = genericService.findById(genericId);
        if (foundGeneric.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            final GenericDto genericDto = GenericDtoMapper.map(foundGeneric.get());
            return new ResponseEntity<>(genericDto, HttpStatus.FOUND);
        }
    }

    @GetMapping(ROOT_API_PATH)
    public ResponseEntity<List<GenericDto>> retrieveAll() {
        log.debug("GET @ " + ROOT_API_PATH);
        final List<GenericObject> listFound = genericService.listAll();
        final List<GenericDto> listFoundDtos = listFound.stream()
                .map(generic -> GenericDtoMapper.map(generic)).toList();
        final ResponseEntity<List<GenericDto>> response = new ResponseEntity<>(listFoundDtos,
                HttpStatus.OK);
        return response;
    }

    @PutMapping(ROOT_API_PATH +"/{genericId}")
    public ResponseEntity<GenericDto> update(@PathVariable final String genericId,
                                             @RequestBody final GenericDto genericDto) {
        log.info("PUT @ " + ROOT_API_PATH + "/" + genericId + " : " + genericDto.toString());
        final GenericObject genericObject = GenericDtoMapper.map(genericDto);
        if (!genericService.isPresent(genericObject)) {
            final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                    GenericDtoMapper.map(genericService.save(genericObject)), HttpStatus.CREATED);
            return responseEntity;
        }
        final ResponseEntity<GenericDto> responseEntity = new ResponseEntity<> (
                GenericDtoMapper.map(genericService.save(genericObject)), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(ROOT_API_PATH +"/{genericId}")
    public ResponseEntity deleteById(@PathVariable String genericId) {
        log.info("DELETE @ " + ROOT_API_PATH + " : " + genericId.toString());
        genericService.deleteById(genericId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
