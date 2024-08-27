package com.atm.exceptions;

import com.atm.models.Banknote;

public class UnsupportedBanknoteException extends RuntimeException {
    public UnsupportedBanknoteException(Banknote banknote) {
        super(String.format("%s banknotes doesn't supported now", banknote.getTitle()));
    }
}
