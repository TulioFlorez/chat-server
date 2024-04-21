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
	        // Crear usuarios
	        UserDetails user1 = User.builder()
	            .username("user1")
	            .password(passwordEncoder().encode("chatpassword"))
	            .roles("USER")
	            .build();

	        UserDetails user2 = User.builder()
	            .username("user2")
	            .password(passwordEncoder().encode("newpassword"))
	            .roles("USER")
	            .build();

	        UserDetails admin = User.builder()
	            .username("admin")
	            .password(passwordEncoder().encode("adminpassword"))
	            .roles("ADMIN")
	            .build();

	        // Retornar InMemoryUserDetailsManager con los usuarios
	        return new InMemoryUserDetailsManager(user1, user2, admin);
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .antMatchers("/api/messages").authenticated() // Rutas autenticadas
	                .anyRequest().permitAll() // Otras rutas permitidas sin autenticación
	                .and()
	            .httpBasic() // Usar autenticación básica HTTP
	            .and()
	            .cors().and()
	            .csrf().disable(); // Desactivar CSRF
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	}