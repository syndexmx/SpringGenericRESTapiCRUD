package com.github.syndexmx.genericspring.services.impl;


import com.github.syndexmx.genericspring.domain.Generic;
import com.github.syndexmx.genericspring.domain.TestGenericSupplier;
import com.github.syndexmx.genericspring.entities.GenericEntity;
import com.github.syndexmx.genericspring.repositories.GenericRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.syndexmx.genericspring.entities.GenericEntity.genericToGenericEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenericServiceImplTest {

    @Mock
    GenericRepository genericRepository;

    @InjectMocks
    private GenericServiceImpl underTest;

    @Test
    public void testThatGenericIsCreated() {
        final Generic generic = TestGenericSupplier.getTestGeneric();
        final GenericEntity genericEntity = genericToGenericEntity(generic);
        when(genericRepository.save(eq(genericEntity))).thenReturn(genericEntity);
        final Generic savedGeneric = underTest.create(generic);
        assertEquals(generic, savedGeneric);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Generic nonExistentGeneric = TestGenericSupplier.getTestNonExistentGeneric();
        final UUID nonExistentUuid = nonExistentGeneric.getGenericId();
        when(genericRepository.findById(eq(nonExistentUuid))).thenReturn(Optional.empty());
        final Optional<Generic> foundGeneric = underTest.findById(nonExistentUuid);
        assertEquals(Optional.empty(), foundGeneric);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Generic generic = TestGenericSupplier.getTestGeneric();
        final GenericEntity genericEntity = GenericEntity.genericToGenericEntity(generic);
        final UUID uuid = generic.getGenericId();
        when(genericRepository.findById(eq(uuid))).thenReturn(Optional.of(genericEntity));
        final Optional<Generic> foundGeneric = underTest.findById(uuid);
        assertEquals(Optional.of(generic), foundGeneric);
    }

    @Test
    public void testListGenericsReturnsEmptyListWhenAbsent() {
        when(genericRepository.findAll()).thenReturn(new ArrayList<GenericEntity>());
        final List<Generic> result = underTest.listGenerics();
        assertEquals(0, result.size());
    }

    @Test
    public void testListGenericsReturnsListWhenExist() {
        final Generic generic = TestGenericSupplier.getTestGeneric();
        final GenericEntity genericEntity = genericToGenericEntity(generic);
        List<GenericEntity> listOfExisting = new ArrayList<>(List.of(genericEntity));
        when(genericRepository.findAll()).thenReturn(listOfExisting);
        final List<Generic> result = underTest.listGenerics();
        assertEquals(listOfExisting.size(), result.size());
    }

}
