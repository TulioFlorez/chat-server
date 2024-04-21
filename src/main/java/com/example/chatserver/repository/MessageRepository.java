package com.example.chatserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatserver.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
    // Método para recuperar mensajes por el nombre de la sala de chat, ordenados desde el primero hasta el último
    List<Message> findByChatRoomNameOrderByCreatedAtAsc(String chatRoomName);

}