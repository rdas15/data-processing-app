package com.velotio.dataprocessor.service;

import com.velotio.dataprocessor.model.NotificationMessage;

public interface EventNotificationService {

    void notifyIngestionCompletion(NotificationMessage message);

    void notifyProcessingCompletion(NotificationMessage message);
}
