package com.atm.exceptions;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException(int requestedAmount, int totalBalance) {
        super(String.format("Not enough balance to withdraw %d but got %d", requestedAmount, totalBalance));
    }
}
