package com.github.syndexmx.genericspring.services.impl;


import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.domain.TestGenericSupplier;
import com.github.syndexmx.genericspring.repositories.entities.GenericEntity;
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

import static com.github.syndexmx.genericspring.repositories.mappers.GenericEntityMapper.genericToGenericEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenericObjectServiceImplTest {

    @Mock
    GenericRepository genericRepository;

    @InjectMocks
    private GenericServiceImpl underTest;

    @Test
    public void testThatGenericIsCreated() {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericEntity genericEntity = genericToGenericEntity(genericObject);
        when(genericRepository.save(eq(genericEntity))).thenReturn(genericEntity);
        final GenericObject savedGenericObject = underTest.create(genericObject);
        assertEquals(genericObject, savedGenericObject);
    }

    @Test
    public void testThatGenericIsSaved() {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericEntity genericEntity = genericToGenericEntity(genericObject);
        when(genericRepository.save(eq(genericEntity))).thenReturn(genericEntity);
        final GenericObject savedGenericObject = underTest.save(genericObject);
        assertEquals(genericObject, savedGenericObject);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final GenericObject nonExistentGenericObject = TestGenericSupplier.getTestNonExistentGeneric();
        final String nonExistentId = nonExistentGenericObject.getGenericId();
        when(genericRepository.findById(eq(UUID.fromString(nonExistentId)))).thenReturn(Optional.empty());
        final Optional<GenericObject> foundGeneric = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundGeneric);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericEntity genericEntity = genericToGenericEntity(genericObject);
        final String id = genericObject.getGenericId();
        when(genericRepository.findById(eq(UUID.fromString(id)))).thenReturn(Optional.of(genericEntity));
        final Optional<GenericObject> foundGeneric = underTest.findById(id);
        assertEquals(Optional.of(genericObject), foundGeneric);
    }

    @Test
    public void testListGenericsReturnsEmptyListWhenAbsent() {
        when(genericRepository.findAll()).thenReturn(new ArrayList<GenericEntity>());
        final List<GenericObject> result = underTest.listGenerics();
        assertEquals(0, result.size());
    }

    @Test
    public void testListGenericsReturnsListWhenExist() {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final GenericEntity genericEntity = genericToGenericEntity(genericObject);
        List<GenericEntity> listOfExisting = new ArrayList<>(List.of(genericEntity));
        when(genericRepository.findAll()).thenReturn(listOfExisting);
        final List<GenericObject> result = underTest.listGenerics();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(genericRepository.existsById(any())).thenReturn(false);
        final GenericObject nonExistentGenericObject = TestGenericSupplier.getTestNonExistentGeneric();
        final String nonExistentUuid = nonExistentGenericObject.getGenericId();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final String genericId = genericObject.getGenericId();
        when(genericRepository.existsById(UUID.fromString(genericId))).thenReturn(true);
        boolean result = underTest.isPresent(genericId);
        assertTrue(result);
    }

    @Test
    public void testThatGenericIsPresentReturnsFalseWhenAbsent() {
        when(genericRepository.existsById(any())).thenReturn(false);
        final GenericObject nonExistentGenericObject = TestGenericSupplier.getTestNonExistentGeneric();
        boolean result = underTest.isPresent(nonExistentGenericObject);
        assertFalse(result);
    }

    @Test
    public void testThatGenericIsPresentReturnsTrueWhenExists() {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final String genericId = genericObject.getGenericId();
        when(genericRepository.existsById(UUID.fromString(genericId))).thenReturn(true);
        boolean result = underTest.isPresent(genericObject);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteGenericDeletesGeneric() {
        final GenericObject genericObject = TestGenericSupplier.getTestGeneric();
        final String genericId = genericObject.getGenericId();
        underTest.deleteGenericById(genericId);
        verify(genericRepository).deleteById(eq(UUID.fromString(genericId)));
    }
}
