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
import com.YomiOluwadara.conferencedemo.models.Attendee;
import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.models.Speaker;
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

		// making the session id a class variable so it could be shared among all the
		// class member methods
		final Long session_id = (long) 6;

		@BeforeEach
		void setup() throws Exception {
			// needed from mockito API to initialize mocks and make them available in
			// SessionController and SessiosSerives classes where they will be used
			MockitoAnnotations.initMocks(this);

			// creating an instance of the Sessions class so we can use an instance of the
			// class Session to call the setter methods of the class that would be useful in
			// setting each Session variables.These variables in the session class all needs
			// to be valued so we can have a Session Object
			Session session = new Session();
			// session = new Session();

			// using setters to set other variables for the object of a Session

			session.setSession_name("test session");
			session.setSession_description("I'm being tested");
			session.setSession_length(60);
			// creating a list object so we can add speakers and set value for the
			// setSpeakers()
			List<Speaker> speakers = new ArrayList<Speaker>();
			// speakers.add(new Speaker().setFirst_name("first_name"));
			session.setSpeakers(speakers);
			List<Attendee> attendees = new ArrayList<Attendee>();
			session.setAttendees(attendees);

		}

		@Test
		@DisplayName("find one seesion given sesion id")
		void findOneSessionTest() {
			// testing the "actual" result in the SessionsService class )- a session object
			// ( having all the expected session attributes)
			when(sessionsService.findOneSession(anyLong())).thenReturn(session);

			Session sessionRestController = sessionsContoller.get(session_id);
			// testing that user id that is being passed is not null
			assertNotNull(sessionRestController);
			// assert that other Session variable from the session service are also equal to
			// the values returned in the session controller (expected : session service ,
			// actual session controller)
			assertEquals(session_id, sessionRestController.getSession_id());
			assertEquals(session.getSession_name(), sessionRestController.getSession_name());
			assertEquals(session.getSession_description(), sessionRestController.getSession_description());
			assertEquals(session.getSession_length(), sessionRestController.getSession_length());
			assertEquals(session.getSpeakers(), sessionRestController.getSpeakers());
			assertEquals(session.getAttendees(), sessionRestController.getAttendees());

		}

	}

}
