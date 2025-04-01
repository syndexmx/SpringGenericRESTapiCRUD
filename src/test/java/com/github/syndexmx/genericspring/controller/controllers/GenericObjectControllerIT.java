package com.github.syndexmx.genericspring.controller.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.controller.mappers.GenericDtoMapper;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.domain.GenericObjectTestSupplierKit;
import com.github.syndexmx.genericspring.controller.dtos.GenericDto;
import com.github.syndexmx.genericspring.services.GenericService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TemplatedAnnotation
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenericObjectControllerIT {

    private final String ROOT_API_PATH = "/api/v0/generics";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenericService genericService;

    @Autowired
    private GenericDtoMapper genericDtoMapper;

    @Test
    public void testThatGenericIsCreated() throws Exception {
        GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final GenericDto genericDto = genericDtoMapper.genericToGenericDto(genericObject);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String genericJson = objectMapper.writeValueAsString(genericDto);
        mockMvc.perform(MockMvcRequestBuilders.post(ROOT_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(genericJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        List<GenericObject> savedGenericList = genericService.listAll();
        assertEquals(1, savedGenericList.size());
        GenericObject savedGeneric = savedGenericList.get(0);
        final UUID id = savedGeneric.getId();
        genericObject.setId(id);
        assertEquals(genericObject, savedGeneric);
    }

    @Test
    public void testThatGenericIsUpdated() throws Exception {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        GenericObject savedGeneric = genericService.create(genericObject);
        final UUID id = savedGeneric.getId();
        GenericObject modifiedGenericObject = GenericObjectTestSupplierKit.getModifiedTestGeneric();
        modifiedGenericObject.setId(id);
        final GenericDto modifiedGenericDto = genericDtoMapper.genericToGenericDto(modifiedGenericObject);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedGenericJson = modifiedObjectMapper.writeValueAsString(modifiedGenericDto);
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_API_PATH + "/" + id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedGenericJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedGenericJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestNonExistentGeneric();
        final UUID id = genericObject.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsGenericWhenExists() throws Exception {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final GenericObject genericObjectSaved = genericService.create(genericObject);
        final UUID id = genericObjectSaved.getId();
        final GenericDto genericDto = genericDtoMapper.genericToGenericDto(genericObjectSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String genericJson = objectMapper.writeValueAsString(genericDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(genericJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final GenericObject genericObjectSaved = genericService.create(genericObject);
        final GenericDto genericDto = genericDtoMapper.genericToGenericDto(genericObjectSaved);
        final List<GenericDto> listGenericDto = new ArrayList<>(List.of(genericDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String genericListJson = objectMapper.writeValueAsString(listGenericDto);
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_API_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(genericListJson));

    }

    @Test
    public void testThatDeleteGenericByIdReturnsHttp204WhenAbsent() throws Exception {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final UUID id = genericObject.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteGenericByIdDeletesGeneric() throws Exception {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final GenericObject savedGenericObject = genericService.save(genericObject);
        final UUID id = savedGenericObject.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_API_PATH + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
