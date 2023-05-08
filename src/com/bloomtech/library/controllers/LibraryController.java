package com.bloomtech.library.controllers;

import com.bloomtech.library.models.CheckableAmount;
import com.bloomtech.library.models.Library;
import com.bloomtech.library.services.LibraryService;
import com.bloomtech.library.views.LibraryAvailableCheckouts;
import com.bloomtech.library.views.OverdueCheckout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/libraries")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping(value = "")
    public ResponseEntity<?> getLibraries() {
        List<Library> libraries = libraryService.getLibraries();
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getLibrary(@PathVariable String name) {
        Library library = libraryService.getLibraryByName(name);
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @GetMapping(value = "/{name}/overdue")
    public ResponseEntity<?> getOverdueCheckouts(@PathVariable String name) {
        List<OverdueCheckout> overdue = libraryService.getOverdueCheckouts(name);
        return new ResponseEntity<>(overdue, HttpStatus.OK);
    }

    @GetMapping(value = "/{name}/checkables/{isbn}")
    public ResponseEntity<?> getCheckableAmount(@PathVariable String name, @PathVariable String isbn) {
        CheckableAmount checkableAmount = libraryService.getCheckableAmount(name, isbn);
        return new ResponseEntity<>(checkableAmount, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{isbn}")
    public ResponseEntity<?> findLibrariesWithCheckable(@PathVariable String isbn) {
        List<LibraryAvailableCheckouts> availableCheckouts = libraryService.getLibrariesWithAvailableCheckout(isbn);
        return new ResponseEntity<>(availableCheckouts, HttpStatus.OK);
    }
}
