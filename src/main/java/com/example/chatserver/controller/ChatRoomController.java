package com.example.chatserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatserver.model.ChatRoom;
import com.example.chatserver.service.ChatRoomService;

@RestController
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/api/chatrooms")
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoomDto) {
        return chatRoomService.createChatRoom(chatRoomDto);
    }
}