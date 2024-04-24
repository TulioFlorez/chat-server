package com.example.chatserver.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chatserver.dto.ChatDto;
import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.dto.MessageDto;
import com.example.chatserver.exception.ChatRoomNotFoundException;
import com.example.chatserver.model.ChatRoom;
import com.example.chatserver.model.Message;
import com.example.chatserver.repository.ChatRoomRepository;
import com.example.chatserver.repository.MessageRepository;
import com.example.chatserver.service.MessageService;
import com.example.chatserver.service.UserService;

@Service
public class MessageServiceImpl implements MessageService {

	// Repository to access messages in the database
	private final MessageRepository messageRepository;

	// Service to retrieve information about the current user
	private final UserService userService;
	
    private final ChatRoomRepository chatRoomRepository;

	// Constructor
	@Autowired
	public MessageServiceImpl(MessageRepository messageRepository, UserService userService, ChatRoomRepository chatRoomRepository) {
		this.messageRepository = messageRepository;
		this.userService = userService;
		this.chatRoomRepository = chatRoomRepository;
	}

	/**
	 * Sends a message created from the data provided by the user.
	 * 
	 * @param message The message provided by the user
	 * @return The sent message saved in the database
	 */
	public Message sentMessage(MessageDto message) {

	    // Get the chat room name from the DTO
	    String roomName = message.getChatRoom();
	    
	    // Search for the chat room by its name in the database
	    Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByName(roomName);
	    
	    // Check if the chat room exists
	    if (optionalChatRoom.isPresent()) {
	        // Create a message entity and set the provided content
	        Message messageEntity = new Message();
	        messageEntity.setUser(userService.getCurrentUsername()); 
	        messageEntity.setContent(message.getContent());
	        messageEntity.setChatRoom(optionalChatRoom.get()); 

	        // Save the message in the database and return it
	        return messageRepository.save(messageEntity);
	    } else {
	    	// Handle the case where the chat room does not exist
	        throw new ChatRoomNotFoundException("Chat room '" + roomName + "' does not exist.");
	    }
	}
	
	@Override
	public ChatRoomDto RetrieveMessagesbyRoom(String chatRoomName) {
	    // Retrieve messages from the specific chat room
	    List<Message> messages = messageRepository.findByChatRoomNameOrderByCreatedAtAsc(chatRoomName);
	    
	    // Map messages to ChatDto objects
	    List<ChatDto> chatDtos = messages.stream()
	                                     .map(this::mapToChatDto)
	                                     .collect(Collectors.toList());
	    
	    // Create a ChatRoomDto with the name of the room and the mapped message list
	    ChatRoomDto chatRoomDto = new ChatRoomDto();
	    chatRoomDto.setRoom(chatRoomName);
	    chatRoomDto.setChat(chatDtos);
	
	    return chatRoomDto;
	}


	@Override
	public void deleteLastUserMessage() {
		Message lastUserMessage = messageRepository.findFirstByUserOrderByCreatedAtDesc(userService.getCurrentUsername());
        if (lastUserMessage != null) {
            messageRepository.delete(lastUserMessage);
        }
    }
	
	private ChatDto mapToChatDto(Message message) {
	    ChatDto chatDto = new ChatDto();
	    chatDto.setUser(message.getUser());
	    chatDto.setMessage(message.getContent());
	    return chatDto;
	}
}