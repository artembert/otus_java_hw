package com.atm.exceptions;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException(int requestedAmount) {
        super(String.format("Not enough balance to withdraw %d", requestedAmount));
    }
}
