package com.atm;

import com.atm.models.Banknote;
import java.util.Map;

public interface WadOfCash {
    void addBanknotes(Banknote banknote, int count);

    void removeBanknotes(Banknote banknote, int count);

    int getTotalSum();

    Map<Banknote, Integer> getBanknotes();
}
