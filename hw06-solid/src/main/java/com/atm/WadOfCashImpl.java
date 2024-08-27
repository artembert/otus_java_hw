package com.atm;

import com.atm.models.Banknote;
import com.atm.models.WadOfCash;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WadOfCashImpl implements WadOfCash {
    private final Map<Banknote, Integer> banknotes = new HashMap<>();

    @Override
    public void addBanknotes(Banknote banknote, int count) {
        banknotes.put(banknote, banknotes.getOrDefault(banknote, 0) + count);
    }

    @Override
    public void removeBanknotes(Banknote banknote, int count) {
        banknotes.put(banknote, banknotes.getOrDefault(banknote, 0) - count);
    }

    @Override
    public Map<Banknote, Integer> getBanknotes() {
        return Collections.unmodifiableMap(banknotes);
    }
}
