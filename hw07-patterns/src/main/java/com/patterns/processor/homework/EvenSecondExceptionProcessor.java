package com.patterns.processor.homework;

import com.patterns.model.Message;
import com.patterns.processor.Processor;
import com.patterns.processor.homework.exceptions.EvenSecondException;

public class EvenSecondExceptionProcessor implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public EvenSecondExceptionProcessor(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        var second = dateTimeProvider.getNow().getSecond();
        if (second % 2 == 0) {
            throw new EvenSecondException(second);
        }
        return message;
    }
}
