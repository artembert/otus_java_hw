package com.patterns.handler;

import com.patterns.listener.Listener;
import com.patterns.model.Message;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);

    void removeListener(Listener listener);
}
