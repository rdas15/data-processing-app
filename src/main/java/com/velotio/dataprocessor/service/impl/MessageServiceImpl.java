package com.velotio.dataprocessor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.velotio.dataprocessor.model.NotificationMessage;
import com.velotio.dataprocessor.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void send(String queueName, NotificationMessage message) {
        jmsTemplate.convertAndSend(queueName, message);
    }

    @Override
    public void sendStr(String queueName, String message) {
        jmsTemplate.convertAndSend(queueName, message);
    }

    @Override
    public String receive(String queueName) {
        jmsTemplate.setReceiveTimeout(10000);
        return jmsTemplate.receiveAndConvert(queueName).toString();
    }
}
