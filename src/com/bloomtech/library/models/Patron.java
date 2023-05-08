package com.bloomtech.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    String name;
    int age;

    @JsonIgnoreProperties(value = "patron")
    private List<LibraryCard> libraryCards = new ArrayList<>();

    public Patron() {
    }

    public Patron(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<LibraryCard> getLibraryCards() {
        return libraryCards;
    }

    public void setLibraryCards(List<LibraryCard> libraryCards) {
        this.libraryCards = libraryCards;
    }
}
