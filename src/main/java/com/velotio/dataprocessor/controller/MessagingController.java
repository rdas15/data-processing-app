package com.velotio.dataprocessor.controller;

public interface MessagingController {

    void sendMessage(String queueName, String message);

    String receiveMessage(String queueName);
}
