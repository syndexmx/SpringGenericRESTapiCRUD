package com.github.syndexmx.genericspring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.domain.TestGenericSupplier;
import com.github.syndexmx.genericspring.controllers.dtos.GenericDto;
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

import static com.github.syndexmx.genericspring.controllers.mappers.GenericDtoMapper.genericToGenericDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenericObjectControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenericService genericService;

    @Test
    public void testThatGenericIsCreated() throws Exception {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericDto genericDto = genericToGenericDto(genericObject);
        final ObjectMapper objectMappper = new ObjectMapper();
        final String genericJson = objectMappper.writeValueAsString(genericDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v0/generics")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(genericJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(genericJson));
    }

    @Test
    public void testThatGenericIsUpdated() throws Exception {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericDto genericDto = genericToGenericDto(genericObject);
        final ObjectMapper objectMappper = new ObjectMapper();
        final String genericJson = objectMappper.writeValueAsString(genericDto);
        final GenericObject modifiedGenericObject = TestGenericSupplier.getModifiedTestGeneric();
        final GenericDto modifiedGenericDto = genericToGenericDto(modifiedGenericObject);
        final ObjectMapper modifiedObjectMapper = new ObjectMapper();
        final String modifiedGenericJson = modifiedObjectMapper.writeValueAsString(modifiedGenericDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v0/generics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(genericJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(genericJson));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v0/generics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifiedGenericJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(modifiedGenericJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final GenericObject genericObject = TestGenericSupplier.getTestNonExistentGeneric();
        final String genericId = genericObject.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/generics/" + genericId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsGenericWhenExists() throws Exception {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericObject genericObjectSaved = genericService.create(genericObject);
        final String genericId = genericObjectSaved.getId();
        final GenericDto genericDto = genericToGenericDto(genericObjectSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String genericJson = objectMapper.writeValueAsString(genericDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/generics/" + genericId))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(genericJson));
    }

    @Test
    public void testThatRetrieveAllReturnsEmptyWhenAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/generics"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatRetrieveAllReturnsListWhenExist() throws Exception {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericObject genericObjectSaved = genericService.create(genericObject);
        final GenericDto genericDto = genericToGenericDto(genericObjectSaved);
        final List<GenericDto> listGenericDto = new ArrayList<>(List.of(genericDto));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String genericListJson = objectMapper.writeValueAsString(listGenericDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/generics"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(genericListJson));

    }

    @Test
    public void testThatDeleteGenericByIdReturnsHttp204WhenAbsent() throws Exception {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final String genericId = genericObject.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v0/generics/" + genericId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteGenericByIdDeletesGeneric() throws Exception {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericObject savedGenericObject = genericService.save(genericObject);
        final String genericId = savedGenericObject.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v0/generics/" + genericId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
