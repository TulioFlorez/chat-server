package com.example.chatserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.chatserver.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
	
    // Define el método findByName para buscar una sala de chat por su nombre
    @Query("SELECT cr FROM ChatRoom cr WHERE cr.name = ?1")
    Optional<ChatRoom> findByName(String name);

}