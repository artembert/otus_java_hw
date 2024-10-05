package com.patterns.listener.homework;

import com.patterns.listener.Listener;
import com.patterns.model.Message;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {
    private final Map<Long, Message> history = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        history.put(msg.getId(), msg.clone());
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(history.get(id));
    }
}
