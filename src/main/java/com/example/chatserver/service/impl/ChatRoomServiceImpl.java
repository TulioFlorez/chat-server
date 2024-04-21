package com.example.chatserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chatserver.model.ChatRoom;
import com.example.chatserver.repository.ChatRoomRepository;
import com.example.chatserver.service.ChatRoomService;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    
    public ChatRoom createChatRoom(ChatRoom chatRoomDto) {
        // Crear una nueva instancia de ChatRoom a partir de los datos del DTO
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(chatRoomDto.getName());
        
        // Guardar la sala de chat en la base de datos
        return chatRoomRepository.save(chatRoom);
    }
}
