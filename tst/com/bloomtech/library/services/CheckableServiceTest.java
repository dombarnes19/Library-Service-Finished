package com.bloomtech.library.services;

import com.amazonaws.event.DeliveryMode;
import com.bloomtech.library.exceptions.CheckableNotFoundException;
import com.bloomtech.library.exceptions.ResourceExistsException;
import com.bloomtech.library.models.Library;
import com.bloomtech.library.models.checkableTypes.*;
import com.bloomtech.library.repositories.CheckableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CheckableServiceTest {

    @Autowired
    private CheckableService checkableService;

    @MockBean
    private CheckableRepository checkableRepository;

    private List<Checkable> checkables;

    @BeforeEach
    void init() {
        //Initialize test data
        checkables = new ArrayList<>();

        checkables.addAll(
                Arrays.asList(
                        new Media("1-0", "The White Whale", "Melvin H", MediaType.BOOK),
                        new Media("1-1", "The Sorcerer's Quest", "Ana T", MediaType.BOOK),
                        new Media("1-2", "When You're Gone", "Complaining at the Disco", MediaType.MUSIC),
                        new Media("1-3", "Nature Around the World", "DocuSpecialists", MediaType.VIDEO),
                        new ScienceKit("2-0", "Anatomy Model"),
                        new ScienceKit("2-1", "Robotics Kit"),
                        new Ticket("3-0", "Science Museum Tickets"),
                        new Ticket("3-1", "National Park Day Pass")
                )
        );
    }

    //TODO: Write Unit Tests for all CheckableService methods and possible Exceptions
    @Test
    void getAll() {
        when(checkableRepository.findAll()).thenReturn(checkables);
        List<Checkable> checkables = checkableService.getAll();
        assertEquals(8, checkables.size());
    }

    @Test
    void getByIsbn_validIsbn() {
        Checkable expected = checkables.get(4);
        Mockito.when(checkableRepository.findByIsbn(expected.getIsbn())).thenReturn(Optional.of(expected));
        Checkable result = checkableService.getByIsbn(expected.getIsbn());
        assertEquals(expected, result);
        Mockito.verify(checkableRepository).findByIsbn(expected.getIsbn());
    }

    @Test
    void getByIsbn_IsbnNotFound_ExceptionThrown() {
        when(checkableRepository.findByIsbn(any(String.class))).thenReturn(Optional.empty());
        assertThrows(CheckableNotFoundException.class, () -> {
            checkableService.getByIsbn("Non-Existent Checkable");
        });
    }

    @Nested
    @DisplayName("Different types")
    class getByType {
        @Test
        void getByTypeTicket_findExistingCheckable() {
            when(checkableRepository.findByType(Ticket.class)).thenReturn(Optional.of(checkables.get(7)));
            Checkable checkable = checkableService.getByType(Ticket.class);
            assertEquals("3-1", checkable.getIsbn());
        }
        @Test
        void getByType_checkable_valid() {
            Checkable expected = checkables.get(4);
            when(checkableRepository.findByType(any(Class.class))).thenReturn(Optional.of(expected));
            Checkable result = checkableService.getByType(expected.getClass());
            assertEquals(expected, result);
            Mockito.verify(checkableRepository).findByType(expected.getClass());

        }
        @Test
        void getByType_checkableInvalid_ReturnException() {
            when(checkableRepository.findByType(any(Class.class))).thenReturn(Optional.empty());
            assertThrows(CheckableNotFoundException.class, () -> {
                checkableService.getByType(checkables.getClass());
            });

        }
    }

    @Test
    void getByType_CheckableByTypeDoesNotExist_throwsCheckableNotFoundException() {
        when(checkableRepository.findByType(ScienceKit.class)).thenThrow(CheckableNotFoundException.class);
        assertThrows(CheckableNotFoundException.class, () -> {
            checkableService.getByType(ScienceKit.class);
        });
    }

    @Test
    void save() {
        when(checkableRepository.findAll()).thenReturn(checkables);
        checkableService.save(new Media("8-4", "More on Coding", "Dominick Barnes", MediaType.BOOK));
        checkableService.save(new Media("8-2","Jacoco test report", "Kevin Monitor", MediaType.MUSIC));
        checkableService.save(new Media("8-3", "Back to the... wait", "Thom the bomb", MediaType.VIDEO));
        Mockito.verify(checkableRepository, atLeast(1)).save(any(Media.class));
    }

    @Test
    void save_existingIsbn_throwsResourceExistsException() {
        when(checkableRepository.findAll()).thenReturn(checkables);
        assertThrows(ResourceExistsException.class, () -> {
            checkableService.save(new Media("1-1", "The Sorcerer's Quest", "Ana T", MediaType.BOOK));
        });
    }
}