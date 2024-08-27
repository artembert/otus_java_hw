package com.atm.models;

public enum Banknote {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500);

    private final int denomination;

    Banknote(int value) {
        denomination = value;
    }

    public int getDenomination() {
        return denomination;
    }

    public String getTitle() {
        return "€" + denomination;
    }
}
