package com.bloomtech.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties(value = "libraryCards")
public class Library {
    private String name;

    @JsonIgnoreProperties(value = "library")
    private Set<LibraryCard> libraryCards = new HashSet<>();
    private List<CheckableAmount> checkables = new ArrayList<>();;

    public Library(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LibraryCard> getLibraryCards() {
        return libraryCards;
    }

    public void setLibraryCards(HashSet<LibraryCard> libraryCards) {
        this.libraryCards = libraryCards;
    }

    public List<CheckableAmount> getCheckables() {
        return checkables;
    }

    public void setCheckables(List<CheckableAmount> checkables) {
        this.checkables = checkables;
    }
}
