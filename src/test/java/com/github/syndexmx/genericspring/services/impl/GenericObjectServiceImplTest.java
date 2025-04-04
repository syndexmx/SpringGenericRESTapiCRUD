package com.github.syndexmx.genericspring.services.impl;

import com.github.syndexmx.genericspring.annotations.TemplatedAnnotation;
import com.github.syndexmx.genericspring.domain.GenericObject;
import com.github.syndexmx.genericspring.domain.GenericObjectTestSupplierKit;
import com.github.syndexmx.genericspring.repository.entities.GenericEntity;
import com.github.syndexmx.genericspring.repository.mappers.GenericEntityMapper;
import com.github.syndexmx.genericspring.repository.repositories.GenericRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@TemplatedAnnotation
@ExtendWith(MockitoExtension.class)
public class GenericObjectServiceImplTest {

    @Mock
    private GenericRepository genericRepository;

    @InjectMocks
    private GenericServiceImpl underTest;

    @Test
    public void testThatGenericIsCreated() {
        GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        GenericEntity genericEntity = GenericEntityMapper.genericToGenericEntity(genericObject);
        when(genericRepository.save(any())).thenReturn(genericEntity);
        final GenericObject savedGenericObject = underTest.create(genericObject);
        genericObject.setId(savedGenericObject.getId());
        assertEquals(genericObject, savedGenericObject);
    }

    @Test
    public void testThatGenericIsSaved() {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final GenericEntity genericEntity = GenericEntityMapper.genericToGenericEntity(genericObject);
        when(genericRepository.save(eq(genericEntity))).thenReturn(genericEntity);
        final GenericObject savedGenericObject = underTest.save(genericObject);
        assertEquals(genericObject, savedGenericObject);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final GenericObject nonExistentGenericObject = GenericObjectTestSupplierKit.getTestNonExistentGeneric();
        final String nonExistentId = nonExistentGenericObject.getId().toString();
        when(genericRepository.findById(eq(UUID.fromString(nonExistentId)))).thenReturn(Optional.empty());
        final Optional<GenericObject> foundGeneric = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundGeneric);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final GenericEntity genericEntity = GenericEntityMapper.genericToGenericEntity(genericObject);
        final String idString = genericObject.getId().toString();
        when(genericRepository.findById(eq(UUID.fromString(idString)))).thenReturn(Optional.of(genericEntity));
        final Optional<GenericObject> foundGeneric = underTest.findById(idString);
        assertEquals(Optional.of(genericObject), foundGeneric);
    }

    @Test
    public void testListGenericsReturnsEmptyListWhenAbsent() {
        when(genericRepository.findAll()).thenReturn(new ArrayList<GenericEntity>());
        final List<GenericObject> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListGenericsReturnsListWhenExist() {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final GenericEntity genericEntity = GenericEntityMapper.genericToGenericEntity(genericObject);
        List<GenericEntity> listOfExisting = new ArrayList<>(List.of(genericEntity));
        when(genericRepository.findAll()).thenReturn(listOfExisting);
        final List<GenericObject> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(genericRepository.existsById(any())).thenReturn(false);
        final GenericObject nonExistentGenericObject = GenericObjectTestSupplierKit.getTestNonExistentGeneric();
        final String nonExistentUuid = nonExistentGenericObject.getId().toString();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final String idString = genericObject.getId().toString();
        when(genericRepository.existsById(UUID.fromString(idString))).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatGenericIsPresentReturnsFalseWhenAbsent() {
        when(genericRepository.existsById(any())).thenReturn(false);
        final GenericObject nonExistentGenericObject = GenericObjectTestSupplierKit.getTestNonExistentGeneric();
        boolean result = underTest.isPresent(nonExistentGenericObject);
        assertFalse(result);
    }

    @Test
    public void testThatGenericIsPresentReturnsTrueWhenExists() {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final String idString = genericObject.getId().toString();
        when(genericRepository.existsById(UUID.fromString(idString))).thenReturn(true);
        boolean result = underTest.isPresent(genericObject);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteGenericDeletesGeneric() {
        final GenericObject genericObject = GenericObjectTestSupplierKit.getTestGeneric();
        final String idString = genericObject.getId().toString();
        underTest.deleteById(idString);
        verify(genericRepository).deleteById(eq(UUID.fromString(idString)));
    }
}
