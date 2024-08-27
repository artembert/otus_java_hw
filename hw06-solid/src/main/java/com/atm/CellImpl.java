package com.atm;

import com.atm.models.Cell;

public class CellImpl implements Cell {
    private int number;

    @Override
    public void addBanknotes(int number) {
        this.number += number;
    }

    @Override
    public void removeBanknotes(int number) {
        this.number -= number;

    }

    @Override
    public int getBanknotesNumber() {
        return number;
    }
}
