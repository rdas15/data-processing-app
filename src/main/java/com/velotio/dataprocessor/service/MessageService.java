package com.velotio.dataprocessor.service;

import com.velotio.dataprocessor.model.NotificationMessage;

public interface MessageService {

    void send(String queueName, NotificationMessage message);

    void sendStr(String queueName, String message);

    String receive(String queueName);
}
