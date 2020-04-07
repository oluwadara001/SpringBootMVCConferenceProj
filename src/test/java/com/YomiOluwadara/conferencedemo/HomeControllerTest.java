/**
 * 
 */
package com.YomiOluwadara.conferencedemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author OO046152
 * 
 * 
 *
 */


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HomeControllerTest {
	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;


	

	
	@Test
	@DisplayName("test should return the welcome message")
	void welcomeMessageTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Hello, welcome to the Yomi conference app project");
	}
	
	
	
	
	/*
	
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
	
	*/
	
	
	
	
	

}
