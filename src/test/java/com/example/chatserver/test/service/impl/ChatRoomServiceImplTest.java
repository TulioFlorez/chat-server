package com.example.chatserver.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.chatserver.model.ChatRoom;
import com.example.chatserver.repository.ChatRoomRepository;
import com.example.chatserver.service.impl.ChatRoomServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ChatRoomServiceImplTest {

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @InjectMocks
    private ChatRoomServiceImpl chatRoomService;

    @Test
    void testCreateChatRoom() {
        // Given
        ChatRoom chatRoomDto = new ChatRoom();
        chatRoomDto.setName("Test Room");

        // Mocking the behavior of chatRoomRepository.save()
        when(chatRoomRepository.save(any(ChatRoom.class))).thenReturn(chatRoomDto);

        // When
        ChatRoom createdChatRoom = chatRoomService.createChatRoom(chatRoomDto);

        // Then
        assertEquals(chatRoomDto, createdChatRoom, "The created chat room should match the DTO");
    }
}