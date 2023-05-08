package com.bloomtech.library.models.checkableTypes;

public class Ticket extends Checkable {
    public Ticket() {
    }

    public Ticket(String isbn, String title) {
        super(isbn, title);
    }
}
