package com.example.chatserver.test.dto;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.GETTER;
import static pl.pojo.tester.api.assertion.Method.SETTER;
import static pl.pojo.tester.api.assertion.Method.TO_STRING;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.chatserver.dto.ChatDto;
import com.example.chatserver.dto.MessageDto;


@ExtendWith(SpringExtension.class)
public class PojoDtoTest {
	@Test
	void test_well_formed() throws Exception{

		assertPojoMethodsFor(ChatDto.class).testing(GETTER, SETTER, CONSTRUCTOR, TO_STRING).areWellImplemented();		
		assertPojoMethodsFor(ChatDto.class).testing(GETTER, SETTER, CONSTRUCTOR, TO_STRING).areWellImplemented();		
		assertPojoMethodsFor(MessageDto.class).testing(GETTER, SETTER, CONSTRUCTOR, TO_STRING).areWellImplemented();		
		
	}	


}

