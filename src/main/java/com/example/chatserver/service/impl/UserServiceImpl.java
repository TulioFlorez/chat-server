package com.example.chatserver.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.chatserver.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public String getCurrentUsername() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return authentication.getName(); // Devuelve el nombre de usuario actual
		}
		return null;
	}
}
