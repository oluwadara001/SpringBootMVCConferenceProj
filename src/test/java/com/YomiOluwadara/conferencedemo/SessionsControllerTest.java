/**
 * 
 */
package com.YomiOluwadara.conferencedemo;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.YomiOluwadara.conferencedemo.controllers.SessionsContoller;
import com.YomiOluwadara.conferencedemo.services.SessionsService;

/**
 * @author OO046152 Yomi Oluwadara
 * 
 *         All the methods in the SessionService class will be tested within the
 *         nested class SessionsServicesTests.
 * 
 *         note the import of import static
 *         org.mockito.ArgumentMatchers.anyLong; that will be used in place of
 *         long id
 * 
 */

class SessionsControllerTest {

	@Nested
	@DisplayName("testing the controller methods in session controller")
	class SessionsServicesTests {
		// used for injecting sessionsService that will be used to call methods in the
		// SessionsService
		@InjectMocks
		SessionsContoller sessionsContoller;
		// mock SessionService class as a class variable for the SessionsServicesTests
		@Mock
		SessionsService sessionsService;

		// initialize mock objects so objects are autowired
		@BeforeEach
		void setup() throws Exception {
			MockitoAnnotations.initMocks(this);
		}

		@Test
		@DisplayName("find one seesion given sesion id")
		void findOneSessionTest() {

			when(sessionsService.findOneSession(anyLong())).thenReturn(null);

		}

	}

}
