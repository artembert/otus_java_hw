package com.atm;

import com.atm.exceptions.CellCapacityExceededException;
import com.atm.exceptions.NotEnoughBanknotesException;
import com.atm.models.Cell;

import java.util.List;

public class BanknoteCells {
    private final List<Cell> cells;

    BanknoteCells(List<Cell> items) {
        this.cells = items;
    }

    void addBanknotes(int count) {
        var rest = count;
        for (var cell : this.cells) {
            var overflow = cell.addBanknotes(rest);
            if (overflow == 0) {
                return;
            }
            rest = overflow;
        }
        if (rest > 0) {
            throw new CellCapacityExceededException();
        }
    }

    void removeBanknotes(int count) {
        var rest = count;
        for (var cell : cells) {
            var lack = cell.removeBanknotes(rest);
            if (lack == 0) {
                return;
            }
            rest = lack;
        }
        if (rest > 0) {
            throw new NotEnoughBanknotesException();
        }
    }

    int getBanknotesNumber() {
        return cells.stream()
                .mapToInt(Cell::getBanknotesNumber)
                .sum();
    }
}
