package com.velotio.dataprocessor.service.impl;

import com.velotio.dataprocessor.model.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.velotio.dataprocessor.service.MessageService;
import com.velotio.dataprocessor.service.EventNotificationService;

@Service
public class EventNotificationServiceImpl implements EventNotificationService {

    @Autowired
    private MessageService messageService;

    @Value("${ingestion.completion-queue}")
    private String ingestionCompletionQueue ;

    @Value("${processing.completion-queue}")
    private String processingCompletionQueue ;

    @Override
    public void notifyIngestionCompletion(NotificationMessage message) {
        messageService.send(ingestionCompletionQueue, message);
    }

    @Override
    public void notifyProcessingCompletion(NotificationMessage message) {
        messageService.send(processingCompletionQueue, message);
    }
}
