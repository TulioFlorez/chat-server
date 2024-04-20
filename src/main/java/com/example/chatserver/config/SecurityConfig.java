package com.example.chatserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("chatuser")
            .password(passwordEncoder().encode("chatpassword"))
            .roles("USER")
            .build();
        
        return new InMemoryUserDetailsManager(user);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // Permite que cualquier solicitud a /api/messages sea accesible solo para usuarios autenticados
                .antMatchers("/api/messages").authenticated()
                // Permite que otras solicitudes se realicen sin autenticación
                .anyRequest().permitAll()
                .and()
            .httpBasic() // Usa autenticación básica HTTP
            .and()
            .csrf().disable(); // Desactivar CSRF
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}