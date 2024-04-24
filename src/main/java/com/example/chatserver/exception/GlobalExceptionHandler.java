package com.example.chatserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<String> handleSendingException(MessageException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error occurred while sending message: " + ex.getMessage());
    }
    
    @ExceptionHandler(ChatRoomNotFoundException.class)
    public ResponseEntity<String> handleChatRoomNotFoundException(ChatRoomNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
