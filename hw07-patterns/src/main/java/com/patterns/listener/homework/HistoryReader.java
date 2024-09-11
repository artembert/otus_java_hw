package com.patterns.listener.homework;

import java.util.Optional;
import com.patterns.model.Message;

public interface HistoryReader {

    Optional<Message> findMessageById(long id);
}
