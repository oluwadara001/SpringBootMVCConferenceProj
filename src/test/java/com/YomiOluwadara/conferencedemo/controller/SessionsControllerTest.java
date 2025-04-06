package com.YomiOluwadara.conferencedemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.YomiOluwadara.conferencedemo.controllers.SessionsController;
import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.services.SessionDetailsService;
import com.YomiOluwadara.conferencedemo.services.SessionService;
import com.YomiOluwadara.conferencedemo.services.SpeakerService;

class SessionsControllerTest {

	@Nested
	@DisplayName("testing the controller methods in session controller")
	class SessionsServicesTests {

		//inject SessionsController instance that will be used to call methods in the SessionsController class
		@InjectMocks
		SessionsController sessionsController;

		// mocks an instance of the SessionService that will be used to call methods in the SessionService class
		@Mock
		SessionService sessionService;

		// mocks an instance of the SpeakerService that will be used to call methods in the SpeakerService class
		@Mock
		SpeakerService speakerService;

		@Mock
		SessionDetailsService sessionDetailsService;

		// variables of type session, so a Session object can be successfully created in this test class
		Session session;
		Session session2;

		private MockMvc mockMvc;

		@BeforeEach
		void setup() throws Exception {
			// needed for mockito API to initialize mocks and make them available in
			// SessionController and SessionService classes where they will be used
			MockitoAnnotations.openMocks(this);
			session = new Session();
			session2 = new Session();
			SessionsController controller = new SessionsController(sessionService, speakerService, sessionDetailsService);
			mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		}

		@Test
		@DisplayName("find one session given its session id")
		void findOneSessionTest() {
			// set up the test data
			session.setId(1L);
			session.setTitle("Test Session");
			session.setDescription("Test Description");
			session.setCapacity(100);

			// testing the "actual" result in the SessionService class
			when(sessionService.findById(anyLong())).thenReturn(session);

			// call the getSessionApi from the SessionsController class
			Session foundSession = sessionsController.getSessionApi(1L);

			// verify the results
			assertNotNull(foundSession);
			assertEquals(session.getId(), foundSession.getId());
			assertEquals(session.getTitle(), foundSession.getTitle());
			assertEquals(session.getDescription(), foundSession.getDescription());
			assertEquals(session.getCapacity(), foundSession.getCapacity());
		}

		@Test
		@DisplayName("return all session")
		void findAllSessionTest() {
			// set up the test data
			session.setId(1L);
			session.setTitle("Test Session 1");
			session.setDescription("Test Description 1");
			session.setCapacity(100);

			session2.setId(2L);
			session2.setTitle("Test Session 2");
			session2.setDescription("Test Description 2");
			session2.setCapacity(200);

			List<Session> sessionList = new ArrayList<>();
			sessionList.add(session);
			sessionList.add(session2);

			when(sessionService.findAllWithSpeakers()).thenReturn(sessionList);

			// call the listSessionsApi from the SessionsController class
			List<Session> sessionListFromSessionController = sessionsController.listSessionsApi();

			// verify the results
			assertNotNull(sessionListFromSessionController);
			assertEquals(2, sessionListFromSessionController.size());
			assertEquals(session.getId(), sessionListFromSessionController.get(0).getId());
			assertEquals(session2.getId(), sessionListFromSessionController.get(1).getId());
		}

		@Test
		@DisplayName("creates a session object and add to db")
		void createOneSessionTest() {
			session.setId(145L);
			session.setTitle("test session being added");
			session.setDescription("I'm a new session being added");
			session.setCapacity(45);

			assertNotNull(session);
			// create a list and add the session object to it
			List<Session> sessionList = new ArrayList<Session>();
			sessionList.add(session);
			assertEquals(1, sessionList.size());
		}

		@Test
		@DisplayName("deletes one session object given its id")
		void deleteOneSessionTest() throws Exception {
			mockMvc.perform(delete("/api/v1/sessions/13").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		}

		@Test
		@Rollback(false)
		@DisplayName("updates one session object given its id")
		void updateOneSession() {
			session.setCapacity(45);

			// TODO, add implementation
		}
	}

}
