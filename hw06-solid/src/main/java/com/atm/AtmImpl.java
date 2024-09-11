package com.atm;

public class AtmImpl implements Atm {
    private final CellStorage cellStorage;

    AtmImpl(CellStorage storage) {
        cellStorage = storage;
    }

    @Override
    public void deposit(WadOfCash wadOfCash) {
        cellStorage.addWadOfCash(wadOfCash);
    }

    @Override
    public WadOfCash withdraw(int amount) {
        return cellStorage.getWadOfCash(amount);
    }

    @Override
    public int getBalance() {
        return cellStorage.getTotalSum();
    }
}
