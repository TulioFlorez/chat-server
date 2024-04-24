package com.example.chatserver.exception;

public class  ChatRoomNotFoundException extends RuntimeException {

    public ChatRoomNotFoundException(String message) {
        super(message);
    }
}