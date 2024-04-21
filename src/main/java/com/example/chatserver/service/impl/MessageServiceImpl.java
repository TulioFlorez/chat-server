package com.example.chatserver.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chatserver.dto.ChatDto;
import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.dto.MessageDto;
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
	@Override
	public Message sentMessage(MessageDto message) {

		   // Obtener el nombre de la sala de chat del DTO
        String roomName = message.getChatRoom();
        
        // Buscar la sala de chat por su nombre en la base de datos
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByName(roomName);
        
        // Verificar si la sala de chat existe
        if (optionalChatRoom.isPresent()) {
            // Crear una entidad de mensaje y asignar el contenido proporcionado
            Message messageEntity = new Message();
            messageEntity.setUser(userService.getCurrentUsername()); // Obtener el usuario actual
            messageEntity.setContent(message.getContent());
            messageEntity.setChatRoom(optionalChatRoom.get()); // Establecer la sala de chat en el mensaje

            // Guardar el mensaje en la base de datos y devolverlo
            return messageRepository.save(messageEntity);
        } else {
            // Manejar el caso en el que la sala de chat no existe
            throw new IllegalArgumentException("Chat room '" + roomName + "' does not exist.");
        }
        
	}
    
	@Override
	public ChatRoomDto RetrieveMessagesbyRoom(String chatRoomName) {
	    // Recuperar mensajes de la sala de chat específica
	    List<Message> messages = messageRepository.findByChatRoomNameOrderByCreatedAtAsc(chatRoomName);
	    
	    // Mapear mensajes a objetos ChatDto
	    List<ChatDto> chatDtos = messages.stream()
	                                     .map(message -> {
	                                         ChatDto chatDto = new ChatDto();
	                                         chatDto.setUser(message.getUser());
	                                         chatDto.setMessage(message.getContent());
	                                         return chatDto;
	                                     })
	                                     .collect(Collectors.toList());
	    
	    // Crear un ChatRoomDto con el nombre de la sala y la lista de mensajes mapeados
	    ChatRoomDto chatRoomDto = new ChatRoomDto();
	    chatRoomDto.setRoom(chatRoomName);
	    chatRoomDto.setChat(chatDtos);

	    // Devolver una lista que contenga el ChatRoomDto creado
	    return chatRoomDto;
	    
	}
	
}