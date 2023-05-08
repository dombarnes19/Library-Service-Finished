package com.bloomtech.library.repositories;

import com.bloomtech.library.datastore.Datastore;
import com.bloomtech.library.models.Library;
import com.bloomtech.library.models.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LibraryRepository {

    @Autowired
    private Datastore datastore;

    public List<Library> findAll() {
        return datastore.getLibraries();
    }

    public Optional<Library> findByName(String name) {
        Optional<Library> library = datastore.getLibraries()
                .stream()
                .filter(l -> l.getName().equals(name))
                .findFirst();
        return library;
    }

    public void save(Library library) {
        datastore.getLibraries().add(library);
    }
}
