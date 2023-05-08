package com.bloomtech.library.models.checkableTypes;

import java.util.HashSet;

public abstract class Checkable {
    private String isbn;
    private String title;

    public Checkable() {
    }

    public Checkable(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
