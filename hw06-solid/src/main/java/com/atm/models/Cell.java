package com.atm.models;

public interface Cell {
    void addBanknotes(int number);
    void removeBanknotes(int number);
    int getBanknotesNumber();
}
