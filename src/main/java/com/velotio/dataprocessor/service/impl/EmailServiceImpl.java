package com.velotio.dataprocessor.service.impl;

import com.velotio.dataprocessor.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Email sent to: " + to + " with subject: " + subject + " and body: " + body);
    }
}
