package com.atm;

import com.atm.exceptions.NotEnoughBalanceException;
import com.atm.exceptions.UnableWithdrawAmountException;
import com.atm.exceptions.UnsupportedBanknoteException;
import com.atm.models.Banknote;
import com.atm.models.Cell;
import com.atm.models.CellStorage;
import com.atm.models.WadOfCash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CellStorageImpl implements CellStorage {
    private final Map<Banknote, Cell> cells;

    CellStorageImpl(Map<Banknote, Cell> cells) {
        this.cells = cells;
    }

    @Override
    public void addWadOfCash(WadOfCash wadOfCash) {
        var unsupportedBanknotes = findUnsupportedBanknotes(wadOfCash);
        if (!unsupportedBanknotes.isEmpty()) {
            throw new UnsupportedBanknoteException(unsupportedBanknotes.getFirst());
        }
        for (Map.Entry<Banknote, Integer> entry : wadOfCash.getBanknotes().entrySet()) {
            var banknote = entry.getKey();
            var count = entry.getValue();
            var cell = cells.get(banknote);
            if (cell == null) {
                throw new UnsupportedBanknoteException(banknote);
            }
            cell.addBanknotes(count);
        }
    }

    @Override
    public WadOfCash getWadOfCash(int amount) {
        if (amount < getTotalSum()) {
            throw new NotEnoughBalanceException(amount);
        }
        var left = amount;
        var wadOfCash = new WadOfCashImpl();
        for (Map.Entry<Banknote, Cell> entry : cells.entrySet()) {
            var banknote = entry.getKey();
            var cell = entry.getValue();
            var banknotesNumber = cell.getBanknotesNumber();
            var denomination = banknote.getDenomination();
            var count = Math.min(left / denomination, banknotesNumber);
            if (count > 0) {
                wadOfCash.addBanknotes(banknote, count);
                left -= count * denomination;
            }
        }
        if (left > 0) {
            throw new UnableWithdrawAmountException(amount);
        }
        return wadOfCash;
    }

    @Override
    public int getTotalSum() {
        int totalSum = 0;
        for (Map.Entry<Banknote, Cell> entry : cells.entrySet()) {
            var banknote = entry.getKey();
            var cell = entry.getValue();
            totalSum += banknote.getDenomination() * cell.getBanknotesNumber();
        }
        return totalSum;
    }


    private List<Banknote> findUnsupportedBanknotes(WadOfCash wadOfCash) {
        List<Banknote> unsupportedBanknotes = new ArrayList<>();
        for (Map.Entry<Banknote, Integer> entry : wadOfCash.getBanknotes().entrySet()) {
            var banknote = entry.getKey();
            var cell = cells.get(banknote);
            if (cell == null) {
                unsupportedBanknotes.add(banknote);
            }
        }
        return unsupportedBanknotes;
    }
}
