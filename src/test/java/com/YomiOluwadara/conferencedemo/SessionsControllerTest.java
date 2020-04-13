/**
 * 
 */
package com.YomiOluwadara.conferencedemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.YomiOluwadara.conferencedemo.controllers.SessionsContoller;
import com.YomiOluwadara.conferencedemo.models.Session;
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
 *         NOTE: there are a lot of in-line comments
 * 
 */

class SessionsControllerTest {

	@Nested
	@DisplayName("testing the controller methods in session controller")
	class SessionsServicesTests {

		// used for injecting SessionsContoller instance that will be used to call
		// methods in the SessionsContoller class
		@InjectMocks
		SessionsContoller sessionsContoller;
		// mocks an instance of the SessionService that will be used to call methods in
		// the SessionService class
		@Mock
		SessionsService sessionsService;

		// bring in a variable of type session, so a Session object can be successfully
		// created in this test class
		Session session;

		@BeforeEach
		void setup() throws Exception {
			// needed from mockito API to initialize mocks and make them available in
			// SessionController and SessiosSerives classes where they will be used
			MockitoAnnotations.initMocks(this);
		}

		@Test
		@DisplayName("find one seesion given sesion id")
		void findOneSessionTest() {

			// Creating an instance of the session class and using it to set session
			// attributes to be tested
			Session session = new Session();
			session.setSession_id(1000);
			session.setSession_name("test session");
			session.setSession_description("I'm being tested");
			session.setSession_length(60);

			// testing the "actual" result in the SessionsService class )- a session object
			// ( having all the expected session attributes)
			when(sessionsService.findOneSession(anyLong())).thenReturn(session);

			// using sessionsContoller object to call the get() and assigning it to type
			// Session.
			// sessionsContoller.get(session.getSession_id());
			Session sessionRestControllerActual = sessionsContoller.get(session.getSession_id());

			// testing that user id that is being passed is not null
			assertNotNull(sessionRestControllerActual.getSession_id());

			// assert that other Session variable from the session service are also equal to
			// the values returned in the session controller (expected : session service ,
			// actual session controller)
			assertEquals(session.getSession_id(), sessionRestControllerActual.getSession_id());
			assertEquals(session.getSession_name(), sessionRestControllerActual.getSession_name());
			assertEquals(session.getSession_description(), sessionRestControllerActual.getSession_description());
			assertEquals(session.getSession_length(), sessionRestControllerActual.getSession_length());
		}

		@Test
		@DisplayName("return all seesions")
		void findAllSessionTest() {
			Session session = new Session();
			session.setSession_id(1000);
			session.setSession_name("test session 1");
			session.setSession_description("session desription1");
			session.setSession_length(60);
			Session session2 = new Session();
			session2.setSession_id(2000);
			session2.setSession_name("test session 2");
			session2.setSession_description("session_description");
			session2.setSession_length(45);
			// for sessionService::create list and add session objects to it
			List<Session> sessionObjectsFromSessionSerive = new ArrayList<Session>();
			sessionObjectsFromSessionSerive.add(session);
			sessionObjectsFromSessionSerive.add(session2);
			when(sessionsService.allSessions()).thenReturn(sessionObjectsFromSessionSerive);
			// for session controller:: call the list() and assign it type List
			List<Session> sessionObjectsFromSessioncontroller = sessionsContoller.list();
			// assert that no null list is returned from session service
			assertNotNull(sessionObjectsFromSessionSerive);
			// check if the two lists has the same size
			assertEquals(sessionObjectsFromSessionSerive.size(), sessionObjectsFromSessioncontroller.size());

		}

	}

}
