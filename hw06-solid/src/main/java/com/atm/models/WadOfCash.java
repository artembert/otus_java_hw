package com.atm.models;

import java.util.Map;

public interface WadOfCash {
    void addBanknotes(Banknote banknote, int count);

    void removeBanknotes(Banknote banknote, int count);

    Map<Banknote, Integer> getBanknotes();
}
