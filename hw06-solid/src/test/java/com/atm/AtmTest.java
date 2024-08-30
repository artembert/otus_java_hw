package com.atm;

import com.atm.exceptions.NotEnoughBalanceException;
import com.atm.exceptions.UnableWithdrawAmountException;
import com.atm.exceptions.UnsupportedBanknoteException;
import com.atm.models.Banknote;
import com.atm.models.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtmTest {

    private Atm atm;

    @BeforeEach
    void setUp() {
        var cellStorageConfig = new HashMap<Banknote, Cell>();
        cellStorageConfig.put(Banknote.TEN, new CellImpl());
        cellStorageConfig.put(Banknote.ONE_HUNDRED, new CellImpl());
        var cellStorage = new CellStorageImpl(cellStorageConfig);
        atm = new AtmImpl(cellStorage);

        var initialWad = new WadOfCashImpl();
        initialWad.addBanknotes(Banknote.TEN, 3);
        initialWad.addBanknotes(Banknote.ONE_HUNDRED, 1);
        atm.deposit(initialWad);
    }

    @Test
    @DisplayName("Current balance should be equal to the initial deposit")
    void currentBalanceTest() {
        assertEquals(130, atm.getBalance());
    }

    @Test
    @DisplayName("Deposit should increase balance")
    void depositIncreasesBalance() {
        var wad = new WadOfCashImpl();
        wad.addBanknotes(Banknote.TEN, 3);

        atm.deposit(wad);

        assertEquals(160, atm.getBalance());
    }

    @Test
    @DisplayName("Withdraw should decrease balance")
    void withdrawDecreasesBalance() {
        System.out.println(atm.getBalance());
        atm.withdraw(30);

        assertEquals(100, atm.getBalance());
    }

    @Test
    @DisplayName("Throws exception when withdrawing more than balance")
    void withdrawThrowsExceptionWhenInsufficientFunds() {
        assertThrows(NotEnoughBalanceException.class, () -> atm.withdraw(500));
    }

    @Test
    @DisplayName("Throws exception when depositing unsupported banknote")
    void withdrawThrowsExceptionWhenUnsupportedBanknote() {
        var wad = new WadOfCashImpl();
        wad.addBanknotes(Banknote.TWENTY, 3);

        assertThrows(UnsupportedBanknoteException.class, () -> atm.deposit(wad));
    }

    @Test
    @DisplayName("Throws exception when withdrawing amount not divisible by smallest denomination")
    void withdrawThrowsExceptionWhenAmountNotDivisibleBySmallestDenomination() {
        assertThrows(UnableWithdrawAmountException.class, () -> atm.withdraw(15));
    }
}