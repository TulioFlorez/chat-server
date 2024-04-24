package com.example.chatserver.test.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.chatserver.dto.ChatDto;
import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.dto.MessageDto;
import com.example.chatserver.model.ChatRoom;
import com.example.chatserver.model.Message;
import com.example.chatserver.repository.ChatRoomRepository;
import com.example.chatserver.repository.MessageRepository;
import com.example.chatserver.service.UserService;
import com.example.chatserver.service.impl.MessageServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserService userService;

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Test
    void testSentMessage() {
        // Given
        MessageDto messageDto = new MessageDto();
        messageDto.setChatRoom("Test Room");
        messageDto.setContent("Test message");

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName("Test Room");

        when(chatRoomRepository.findByName("Test Room")).thenReturn(Optional.of(chatRoom));
        when(userService.getCurrentUsername()).thenReturn("Test User");

        Message savedMessage = new Message();
        savedMessage.setId(1L);
        savedMessage.setUser("Test User");
        savedMessage.setContent("Test message");
        savedMessage.setChatRoom(chatRoom);

        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

        // When
        Message createdMessage = messageService.sentMessage(messageDto);

        // Then
        assertEquals(savedMessage, createdMessage, "The created message should match the saved message");
    }

    @Test
    void testRetrieveMessagesByRoom() {
        // Given
        String chatRoomName = "Test Room";

        Message message1 = new Message();
        message1.setUser("User1");
        message1.setContent("Message 1");

        Message message2 = new Message();
        message2.setUser("User2");
        message2.setContent("Message 2");

        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);

        when(messageRepository.findByChatRoomNameOrderByCreatedAtAsc(chatRoomName)).thenReturn(messages);

        // When
        ChatRoomDto chatRoomDto = messageService.RetrieveMessagesbyRoom(chatRoomName);

        // Then
        assertEquals(chatRoomName, chatRoomDto.getRoom(), "The chat room name should match");
        List<ChatDto> chatDtos = chatRoomDto.getChat();
        assertEquals(2, chatDtos.size(), "The number of messages should match");
        assertEquals("User1", chatDtos.get(0).getUser(), "The user of the first message should match");
        assertEquals("Message 1", chatDtos.get(0).getMessage(), "The content of the first message should match");
        assertEquals("User2", chatDtos.get(1).getUser(), "The user of the second message should match");
        assertEquals("Message 2", chatDtos.get(1).getMessage(), "The content of the second message should match");
    }
}