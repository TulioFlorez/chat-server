package com.example.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatserver.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}