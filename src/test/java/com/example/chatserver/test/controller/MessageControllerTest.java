package com.example.chatserver.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.chatserver.controller.MessageController;
import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.dto.MessageDto;
import com.example.chatserver.model.Message;
import com.example.chatserver.repository.MessageRepository;
import com.example.chatserver.service.MessageService;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @Test
    void testSendMessage() {
        // Arrange
        MessageDto messageDto = new MessageDto();
        messageDto.setChatRoom("room1"); 

        when(messageService.sentMessage(any(MessageDto.class))).thenReturn(new Message());

        // Act
        ChatRoomDto result = messageController.sendMessage(messageDto);
       //TO-DO ASSERT


    }
}