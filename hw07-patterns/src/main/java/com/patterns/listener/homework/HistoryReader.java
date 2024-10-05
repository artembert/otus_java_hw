package com.patterns.listener.homework;

import com.patterns.model.Message;
import java.util.Optional;

public interface HistoryReader {

    Optional<Message> findMessageById(long id);
}
