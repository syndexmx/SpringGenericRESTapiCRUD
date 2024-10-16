package com.github.syndexmx.genericspring.controllers;

import com.github.syndexmx.genericspring.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {

    private final GenericService genericService;

    @Autowired
    private GenericController(GenericService genericService) {
        this.genericService = genericService;
    }

}
