package com.example.chatserver.test.controller;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.chatserver.controller.ChatRoomController;
import com.example.chatserver.model.ChatRoom;
import com.example.chatserver.service.ChatRoomService;

@ExtendWith(MockitoExtension.class)
public class ChatRoomControllerTest {

    @Mock
    private ChatRoomService chatRoomService;

    @InjectMocks
    private ChatRoomController chatRoomController;

    @Test
    public void testCreateChatRoom() {
        // Arrange
        ChatRoom chatRoomDto = new ChatRoom();
        when(chatRoomService.createChatRoom(any(ChatRoom.class))).thenReturn(chatRoomDto);

        // Act
        ChatRoom result = chatRoomController.createChatRoom(chatRoomDto);

        // Assert
        assertNotNull(result);

    }
}