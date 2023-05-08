package com.bloomtech.library.views;

import com.bloomtech.library.models.Checkout;
import com.bloomtech.library.models.Patron;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class OverdueCheckout {
    @JsonIgnoreProperties(value = "libraryCards")
    public Patron patron;
    public Checkout checkout;

    public OverdueCheckout(Patron patron, Checkout checkout) {
        this.patron = patron;
        this.checkout = checkout;
    }

    public Patron getPatron() {
        return patron;
    }

    public Checkout getCheckout() {
        return checkout;
    }
}
