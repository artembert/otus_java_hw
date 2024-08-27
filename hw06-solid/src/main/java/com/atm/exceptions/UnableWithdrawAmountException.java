package com.atm.exceptions;

public class UnableWithdrawAmountException extends RuntimeException {
    public UnableWithdrawAmountException(int requestedAmount) {
        super(String.format("Unable to withdraw %d", requestedAmount));
    }
}
