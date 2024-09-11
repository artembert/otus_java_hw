package com.patterns.listener.homework;

import java.util.Optional;
import com.patterns.listener.Listener;
import com.patterns.model.Message;

public class HistoryListener implements Listener, HistoryReader {

    @Override
    public void onUpdated(Message msg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        throw new UnsupportedOperationException();
    }
}
