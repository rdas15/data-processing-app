package com.velotio.dataprocessor.service;

public interface EmailService {

    void sendEmail(String to, String subject, String body);
}
