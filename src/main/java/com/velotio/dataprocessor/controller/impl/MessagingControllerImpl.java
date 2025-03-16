package com.velotio.dataprocessor.controller.impl;

import com.velotio.dataprocessor.controller.MessagingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.velotio.dataprocessor.service.MessageService;

@RestController
@RequestMapping("/api/v1/messaging")
public class MessagingControllerImpl implements MessagingController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send/{queueName}")
    @Override
    public void sendMessage(@PathVariable String queueName, String message) {
        messageService.sendStr(queueName, message);
    }

    @GetMapping("/receive/{queueName}")
    @Override
    public String receiveMessage(String queueName) {
        return messageService.receive(queueName);
    }
}
