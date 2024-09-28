package com.patterns.processor.homework.exceptions;

public class EvenSecondException extends RuntimeException {
    public EvenSecondException(int second) {
        super(String.format("Even second: %d", second));
    }
}
