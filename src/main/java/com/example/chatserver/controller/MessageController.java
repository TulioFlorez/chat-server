package com.example.chatserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.dto.MessageDto;
import com.example.chatserver.model.Message;
import com.example.chatserver.repository.MessageRepository;
import com.example.chatserver.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageService messageService;

	@GetMapping
	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	@PostMapping
	public ChatRoomDto sendMessage(@RequestBody MessageDto message) {
		try {
		Message messagei = messageService.sentMessage(message);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return messageService.RetrieveMessagesbyRoom(message.getChatRoom());
	}
}