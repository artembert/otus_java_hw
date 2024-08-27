package com.atm.models;

import java.util.Map;

public interface WadOfCash {
    void addBanknotes(Banknote banknote, int count);

    void removeBanknotes(Banknote banknote, int count);

    int getTotalSum();

    Map<Banknote, Integer> getBanknotes();
}
