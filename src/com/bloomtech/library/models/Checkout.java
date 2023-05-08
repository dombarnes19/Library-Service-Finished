package com.bloomtech.library.models;

import com.bloomtech.library.models.checkableTypes.Checkable;

import java.time.LocalDateTime;

public class Checkout {
    private Checkable checkable;
    private LocalDateTime dueDate;

    public Checkout(Checkable checkable, LocalDateTime dueDate) {
        this.checkable = checkable;
        this.dueDate = dueDate;
    }

    public Checkable getCheckable() {
        return checkable;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
