package com.bloomtech.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class LibraryCard {
    @JsonIgnoreProperties("libraryCards")
    private Patron patron;
    @JsonIgnoreProperties("libraryCards")
    private Library library;
    private List<Checkout> checkouts = new ArrayList<>();;

    public LibraryCard() {
    }

    public LibraryCard(Patron patron, Library library) {
        this.patron = patron;
        this.library = library;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }
}
