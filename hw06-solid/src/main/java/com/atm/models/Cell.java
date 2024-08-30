package com.atm.models;

public interface Cell {
    int addBanknotes(int number);
    int removeBanknotes(int number);
    int getBanknotesNumber();
}
