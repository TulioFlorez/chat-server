package com.example.chatserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.dto.MessageDto;
import com.example.chatserver.exception.MessageException;
import com.example.chatserver.model.Message;
import com.example.chatserver.repository.MessageRepository;
import com.example.chatserver.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	private final MessageRepository messageRepository;
	private final MessageService messageService;

	@Autowired
	public MessageController(MessageRepository messageRepository, MessageService messageService) {
		this.messageRepository = messageRepository;
		this.messageService = messageService;
	}

	@GetMapping
	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	@PostMapping
	public ChatRoomDto sendMessage(@RequestBody MessageDto message) {
		try {
			messageService.sentMessage(message);
		} catch (Exception e) {
			throw new MessageException("Error occurred while sending message: " + e.getMessage());
		}
		return messageService.RetrieveMessagesbyRoom(message.getChatRoom());
	}

	@DeleteMapping
	public void deleteLastMessagebyUser() {
		try {
			messageService.deleteLastUserMessage();
		} catch (Exception e) {
			throw new MessageException("Error occurred while deleting last message by user");
		}

	}

}
