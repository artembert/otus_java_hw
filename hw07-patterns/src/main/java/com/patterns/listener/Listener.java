package com.patterns.listener;

import com.patterns.model.Message;

@SuppressWarnings("java:S1135")
public interface Listener {

    void onUpdated(Message msg);
}
