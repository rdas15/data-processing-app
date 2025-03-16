package com.velotio.dataprocessor.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.velotio.dataprocessor.service.DataProcessingService;
import com.velotio.dataprocessor.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.velotio.dataprocessor.model.NotificationMessage;

@Component
public class JmsMessageConsumer {

    @Autowired
    private DataProcessingService dataProcessingService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${email.recipient}")
    private String emailRecipient;

    @JmsListener(destination = "${ingestion.completion-queue}")
    public void receiveMessageFromIngestionQueue(String message) throws JsonProcessingException {
        System.out.println("Received from Ingestion Queue: " + message);
        dataProcessingService.processRawData(objectMapper.readValue(message, NotificationMessage.class));
    }

    @JmsListener(destination = "${processing.completion-queue}")
    public void receiveMessageFromProcessingQueue(String message) {
        System.out.println("Received from Completion Queue: " + message);
        emailService.sendEmail(emailRecipient, "Processing Completed", message);
    }
}
