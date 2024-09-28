package com.patterns.processor.homework;

import com.patterns.model.Message;
import com.patterns.processor.Processor;

public class ProcessorSwapFields implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder().field11(message.getField12()).field12(message.getField11()).build();
    }
}
