
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;

import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.services.SessionsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


class SessionsControllerTest {

	@Nested
	@DisplayName("testing the controller methods in session controller")
	class SessionsServicesTests {

		//inject SessionsController instance that will be used to call methods in the SessionsController class
		@InjectMocks
		SessionsController sessionsController;

		// mocks an instance of the SessionService that will be used to call methods in the SessionService class
		@Mock
		SessionsService sessionsService;
		// variables of type session, so a Session object can be successfully created in this test class
		Session session;
		Session session2;

		private MockMvc mockMvc;

		@BeforeEach
		void setup() throws Exception {
			// needed for mockito API to initialize mocks and make them available in
			// SessionController and SessionService classes where they will be used
			MockitoAnnotations.initMocks(this);
			session = new Session();
			session2 = new Session();
			SessionsController controller = new SessionsController(sessionsService);
			mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		}

		@Test
		@DisplayName("find one session given its session id")
		void findOneSessionTest() {
			//set values for session object
			session.setSession_id(1000);
			session.setSession_name("test session");
			session.setSession_description("I'm being tested");
			session.setSession_length(60);

			// testing the "actual" result in the SessionsService class )- a session object
			// ( having all the expected session attributes)
			when(sessionsService.findOneSession(anyLong())).thenReturn(session);

			//call the getOneSession from the sessionController class
			Session sessionRestControllerActual = sessionsController.getOneSession(session.getSession_id());

			// test that user id that is being passed is not null
			assertNotNull(sessionRestControllerActual.getSession_id());

			// assert that other Session variable from the session service are also equal to
			// the values returned in the session controller
			assertEquals(session.getSession_id(), sessionRestControllerActual.getSession_id());
			assertEquals(session.getSession_name(), sessionRestControllerActual.getSession_name());
			assertEquals(session.getSession_description(), sessionRestControllerActual.getSession_description());
			assertEquals(session.getSession_length(), sessionRestControllerActual.getSession_length());
		}

		@Test
		@DisplayName("return all session")
		void findAllSessionTest() {
			session.setSession_id(1000);
			session.setSession_name("test session 1");
			session.setSession_description("session description one");
			session.setSession_length(60);
			session2.setSession_id(2000);
			session2.setSession_name("test session 2");
			session2.setSession_description("session_description");
			session2.setSession_length(45);
			// for sessionService::create list and add session objects to it
			List<Session> sessionList = new ArrayList<Session>();
			sessionList.add(session);
			sessionList.add(session2);
			when(sessionsService.allSessions()).thenReturn(sessionList);
			// for session controller:: call the list() and assign it type List
			List<Session> sessionListFromSessionController = sessionsController.listAllSessions();
			assertNotNull(sessionList);
			assertEquals(sessionList.size(), sessionListFromSessionController.size());
		}

		@Test
		@DisplayName("creates a session object and add to db")
		void createOneSessionTest() {
			session.setSession_id(145);
			session.setSession_name("test session being added");
			session.setSession_description("I'm a new session being added");
			session.setSession_length(45);
			assertNotNull(session);
			// create a list and add the session object to it
			List<Session> sessionList = new ArrayList<Session>();
			sessionList.add(session);
			assertEquals(1, sessionList.size());
		}

		@Test
		@DisplayName("deletes one session object given its id")
		void deleteOneSessionTest() {

			session.setSession_id(145);
			session.setSession_name("test session being added");
			session.setSession_description("I'm a new sessin being added");
			session.setSession_length(45);
			session2.setSession_id(2000);
			session2.setSession_name("test session 2");
			session2.setSession_description("session_description");
			session2.setSession_length(45);
			List<Session> sessionList = new ArrayList<Session>();
			sessionList.add(session);
			sessionList.add(session2);
			assertNotNull(sessionList);
			sessionsController.deleteOneSession(session.getSession_id());

			sessionList.size();
			// TODO complete implementation
//			assertEquals(1, sessionList.size());
		}

		@Test
		@DisplayName("updates one session object given its id")
		void updateOneSession() {
			// TODO, add implementation
		}
	}

}
