package com.example.chatserver.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.chatserver.model.Message;

@Controller
public class ChatController {



    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        return message;
    }
}
