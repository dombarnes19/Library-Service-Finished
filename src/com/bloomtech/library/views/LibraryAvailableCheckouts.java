package com.bloomtech.library.views;

import com.bloomtech.library.models.Library;

public class LibraryAvailableCheckouts {
    private int available;
    private String libraryName;

    public LibraryAvailableCheckouts(int available, String libraryName) {
        this.available = available;
        this.libraryName = libraryName;
    }

    public int getAvailable() {
        return available;
    }

    public String getLibraryName() {
        return libraryName;
    }
}
