package com.github.syndexmx.genericspring.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.genericspring.domain.Generic;
import com.github.syndexmx.genericspring.domain.TestGenericSupplier;
import com.github.syndexmx.genericspring.dtos.GenericDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenericControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testThatGenericIsCreated() throws Exception {
        final Generic generic = TestGenericSupplier.getTestGeneric();
        final GenericDto genericDto = GenericDto.genericToGenericDto(generic);
        final ObjectMapper objectMappper = new ObjectMapper();
        final String genericJson = objectMappper.writeValueAsString(genericDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v0/generics")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(genericJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(genericJson));
    }

}
