/**
 * 
 */
package com.YomiOluwadara.conferencedemo;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author OO046152
 * 
 * TODO: figure out why these units test are failing in JUnit but passing in postman
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
	
	@Autowired
	private MockMvc mvc;

	
	@Test
	@DisplayName("test should return the app version")
	void getVersionTest() throws Exception {
		//performs an expression and expects
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo("{\"yomi.app.version\": \"1.0.0\"}"))); //Expected value goes here
	}
	
	
	@Test
	@DisplayName("test should return the welcome message")
	void welcomeMessageTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo("Hello, welcome to the Yomi conference app project")));
		
	}
	
	
	
	
	

}
