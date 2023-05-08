package com.bloomtech.library.models;

import com.bloomtech.library.models.checkableTypes.Checkable;

public class CheckableAmount {
    private Checkable checkable;
    private int amount;

    public CheckableAmount(Checkable checkable, int amount) {
        this.checkable = checkable;
        this.amount = amount;
    }

    public Checkable getCheckable() {
        return checkable;
    }

    public int getAmount() {
        return amount;
    }
}
