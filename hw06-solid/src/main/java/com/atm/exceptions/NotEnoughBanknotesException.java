package com.atm.exceptions;

public class NotEnoughBanknotesException extends RuntimeException {
    public NotEnoughBanknotesException() {
        super("Unable to withdraw banknotes");
    }
}
