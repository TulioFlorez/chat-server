package com.example.chatserver.service;

import com.example.chatserver.dto.ChatRoomDto;
import com.example.chatserver.dto.MessageDto;
import com.example.chatserver.model.Message;

public interface MessageService {
	
	Message sentMessage(MessageDto message);
	ChatRoomDto RetrieveMessagesbyRoom(String chatRoomName);

}
