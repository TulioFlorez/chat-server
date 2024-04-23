# Chat Server

This project is a Java-based chat server developed using the Spring Boot framework. It provides both a REST API and WebSocket functionality for managing chat rooms and enabling real-time messaging.

## Features

- Create, join, and delete chat rooms
- Send and receive messages in real-time using WebSocket
- Basic authentication for users (USER1, USER2, ADMIN)

## Endpoints

### ChatRoomController

- **POST /api/chatrooms**: Create a new chat room
- **DELETE /api/chatrooms/{id}**: Delete a chat room by ID

### MessageController

- **GET /api/messages**: Get all messages
- **POST /api/messages**: Send a message
- **DELETE /api/messages**: Delete the last message sent by the current user

### WebSocket

- **/chat**: WebSocket endpoint for real-time messaging

## Authentication

Basic authentication is used for accessing the endpoints. Users USER1 and USER2 have regular access, while ADMIN has administrative privileges.

## Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Spring Boot Starter Security
- H2 Database
- Lombok
- Spring Boot Starter Test
- Spring Boot Starter WebSocket
- Pojo Tester (for testing)

## Development Setup

1. Clone the repository.
2. Install Java 11 and Maven if not already installed.
3. Build the project using `mvn clean install`.
4. Run the application using `mvn spring-boot:run`.

## Configuration

### Security Configuration

Basic authentication is configured using Spring Security. The security configuration class is `SecurityConfig`.

### WebSocket Configuration

WebSocket configuration is done in the class `WebSocketConfig`. It configures the endpoint `/chat` for WebSocket communication.

## Contributing

Contributions are welcome! Please feel free to open issues or pull requests.

