package com.atm;

import com.atm.models.WadOfCash;

public interface Atm {
    void deposit(WadOfCash wadOfCash);

    WadOfCash withdraw(int amount);

    int getBalance();
}