package com.atm.models;

public interface Atm {
    void deposit(WadOfCash wadOfCash);

    WadOfCash withdraw(int amount);

    int getBalance();
}