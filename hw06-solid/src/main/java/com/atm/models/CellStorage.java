package com.atm.models;

public interface CellStorage {
    void addWadOfCash(WadOfCash wadOfCash);

    WadOfCash getWadOfCash(int amount);

    int getTotalSum();
}
