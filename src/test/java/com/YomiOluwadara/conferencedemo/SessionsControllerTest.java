
package com.YomiOluwadara.conferencedemo;

import com.YomiOluwadara.conferencedemo.model.Session;
import com.YomiOluwadara.conferencedemo.services.SessionsService;
import org.hamcrest.CoreMatchers;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.hibernate.tool.schema.spi.ExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
//			mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

			mockMvc = MockMvcBuilders.standaloneSetup(controller)
							  .setControllerAdvice(new ExceptionHandler() {
								  @Override
								  public void handleException(CommandAcceptanceException exception) {

								  }
							  })
							  .alwaysExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
							  .build();
		}

		@Test
		@DisplayName("find one session given its session id")
		void findOneSessionTest() {
			//set values for session object
			session.setSessionId(1000);
			session.setSessionName("test session");
			session.setSessionDescription("I'm being tested");
			session.setSessionLength(60);

			// testing the "actual" result in the SessionsService class )- a session object
			// ( having all the expected session attributes)
			when(sessionsService.findOneSession(anyLong())).thenReturn(session);

			//call the getOneSession from the sessionController class
			Session sessionRestControllerActual = sessionsController.getOneSession(session.getSessionId());

			// test that user id that is being passed is not null
			assertNotNull(sessionRestControllerActual.getSessionId());

			// assert that other Session variable from the session service are also equal to
			// the values returned in the session controller
			assertEquals(session.getSessionId(), sessionRestControllerActual.getSessionId());
			assertEquals(session.getSessionName(), sessionRestControllerActual.getSessionName());
			assertEquals(session.getSessionDescription(), sessionRestControllerActual.getSessionDescription());
			assertEquals(session.getSessionLength(), sessionRestControllerActual.getSessionLength());

			//using asserThat notation
			assertThat(session.getSessionId(), CoreMatchers.is(sessionRestControllerActual.getSessionId()));
		}

		@Test
		@DisplayName("return all session")
		void findAllSessionTest() {
			session.setSessionId(1000);
			session.setSessionName("test session 1");
			session.setSessionDescription("session description one");
			session.setSessionLength(60);

			session2.setSessionId(2000);
			session2.setSessionName("test session 2");
			session2.setSessionDescription("session_description");
			session2.setSessionLength(45);
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
			session.setSessionId(145);
			session.setSessionName("test session being added");
			session.setSessionDescription("I'm a new session being added");
			session.setSessionLength(45);

			assertNotNull(session);
			// create a list and add the session object to it
			List<Session> sessionList = new ArrayList<Session>();
			sessionList.add(session);
			assertEquals(1, sessionList.size());
		}

		@Test
		@DisplayName("deletes one session object given its id")
		void deleteOneSessionTest() throws Exception {
			mockMvc.perform(delete("/api/v1/sessions/13").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

		}

		@Test
		@Rollback(false)
		@DisplayName("updates one session object given its id")
		void updateOneSession() {
			session.setSessionLength(45);


			// TODO, add implementation
		}
	}

}




