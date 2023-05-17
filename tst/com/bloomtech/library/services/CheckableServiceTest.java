package com.bloomtech.library.services;

import com.amazonaws.event.DeliveryMode;
import com.bloomtech.library.exceptions.CheckableNotFoundException;
import com.bloomtech.library.exceptions.ResourceExistsException;
import com.bloomtech.library.models.Library;
import com.bloomtech.library.models.checkableTypes.*;
import com.bloomtech.library.repositories.CheckableRepository;
import org.junit.jupiter.api.BeforeEach;
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

    //TODONE: Write Unit Tests for all CheckableService methods and possible Exceptions
    @Test
    void getAll() {
        when(checkableRepository.findAll()).thenReturn(checkables);
        List<Checkable> checkables = checkableService.getAll();
        assertEquals(8, checkables.size());
    }

    @Test
    void getByIsbn_findCheckablesThatExist() {
        when(checkableRepository.findByIsbn(any(String.class))).thenReturn(Optional.of(checkables.get(6)));
        Checkable checkable = checkableService.getByIsbn("3-0");
        assertEquals("3-0", checkable.getIsbn());
    }

    @Test
    void getByIsbn_CheckableDoesNotExist_throwsCheckableNotFoundException() {
        when(checkableRepository.findByIsbn(any(String.class))).thenThrow(CheckableNotFoundException.class);
        assertThrows(CheckableNotFoundException.class, () -> {
            checkableService.getByIsbn("10-0");
        });
    }

    @Test
    void getByType_findExistingCheckable() {
        when(checkableRepository.findByType(Ticket.class)).thenReturn(Optional.of(checkables.get(7)));
        Checkable checkable = checkableService.getByType(Ticket.class);
        assertEquals("3-1", checkable.getIsbn());
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