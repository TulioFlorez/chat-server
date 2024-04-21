package com.example.chatserver.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/chat") // Ruta específica para la solicitud WebSocket
                .allowedOrigins("*") // Permitir acceso desde cualquier origen
                .allowedMethods("*"); // Permitir todos los métodos HTTP
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/chat") // Ruta específica para la solicitud WebSocket
//                .allowedOrigins("https://websocketking.com") // Permitir acceso desde este origen
//                .allowedMethods("*"); // Permitir todos los métodos HTTP
//    }
}
