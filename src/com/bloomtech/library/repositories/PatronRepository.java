package com.bloomtech.library.repositories;

import com.bloomtech.library.datastore.Datastore;
import com.bloomtech.library.models.Library;
import com.bloomtech.library.models.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PatronRepository {

    @Autowired
    private Datastore datastore;

    public List<Patron> findAll() {
        return datastore.getPatrons();
    }

    public Optional<Patron> findByName(String name) {
        return datastore.getPatrons()
                .stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

    public void save(Patron patron) {
        datastore.getPatrons().add(patron);
    }
}
