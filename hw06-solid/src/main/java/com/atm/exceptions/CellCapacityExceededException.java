package com.atm.exceptions;

public class CellCapacityExceededException extends RuntimeException {
    public CellCapacityExceededException() {
        super("CellCapacityExceededException");
    }
}
