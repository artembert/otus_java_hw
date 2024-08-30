package com.atm;

import com.atm.models.Cell;

public class CellImpl implements Cell {
    private final int capacity;
    private int amount;

    CellImpl(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int addBanknotes(int number) {
        var overflow = number - this.capacity - this.amount;
        if (overflow > 0) {
            this.amount = this.capacity;
            return Math.abs(overflow);
        }
        this.amount += number;
        return 0;
    }

    @Override
    public int removeBanknotes(int number) {
        var lack = number - this.amount;
        if (lack > 0) {
            this.amount = 0;
            return lack;
        }
        this.amount -= number;
        return 0;
    }

    @Override
    public int getBanknotesNumber() {
        return amount;
    }
}
