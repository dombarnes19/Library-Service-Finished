package com.bloomtech.library.repositories;

import com.bloomtech.library.datastore.Datastore;
import com.bloomtech.library.exceptions.LibraryNotFoundException;
import com.bloomtech.library.models.Library;
import com.bloomtech.library.models.LibraryCard;
import com.bloomtech.library.models.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LibraryCardRepository {

    @Autowired
    private Datastore datastore;

    public List<LibraryCard> findAll() {
        return datastore.getLibraryCards();
    }

    public List<LibraryCard> findByLibraryName(String libraryName) {
        return datastore.getLibraryCards()
                .stream()
                .filter(lc -> lc.getLibrary().getName().equals(libraryName))
                .collect(Collectors.toList());
    }

    public List<LibraryCard> findByPatronName(String patronName) {
        return datastore.getLibraryCards()
                .stream()
                .filter(lc -> lc.getPatron().getName().equals(patronName))
                .collect(Collectors.toList());
    }

    public void save(LibraryCard libraryCard) {
        datastore.getLibraryCards().add(libraryCard);
    }
}
