/**
 * 
 */
package com.YomiOluwadara.conferencedemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.controllers.SessionsContoller;
import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.repositories.SessionDAO;

/**
 * @author OO046152
 * 
 */

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
class SessionsContollerTest {
	
	@InjectMocks
	SessionsContoller sessionsContoller;
	@Mock
	SessionDAO sessionDAO;
	
	
	@DisplayName("method should return a list of the sessions")
	@Test
	void listTest() {
		
		
		
	}
	

	
	
	
	
	/*
	
	
	
	
	@DisplayName("method should return a list of the sessions")
	@Test
	void listTest() {
		sessionDAO.findAll();
		verify(sessionDAO).findAll();
		
	}
	*/


	

}
