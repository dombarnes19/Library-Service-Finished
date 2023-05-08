package com.bloomtech.library.repositories;

import com.bloomtech.library.datastore.Datastore;
import com.bloomtech.library.models.Patron;
import com.bloomtech.library.models.checkableTypes.Checkable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CheckableRepository {

    @Autowired
    private Datastore datastore;

    public List<Checkable> findAll() {
        List<Checkable> checkables = new ArrayList<>();

        for (Checkable c : datastore.getCheckables())
        {
            checkables.add(c);
        }
        return checkables;
    }

    public Optional<Checkable> findByIsbn(String isbn) {
        Optional<Checkable> checkable = datastore.getCheckables()
                .stream()
                .filter(c -> c.getIsbn().equals(isbn))
                .findFirst();
        return checkable;
    }

    public Optional<Checkable> findByType(Class type) {
        Optional<Checkable> checkable = datastore.getCheckables()
                .stream()
                .filter(c -> c.getClass().equals(type))
                .findFirst();
        return checkable;
    }

    public void save(Checkable checkable) {
        datastore.getCheckables().add(checkable);
    }
}
