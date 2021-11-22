package com.YomiOluwadara.conferencedemo.controller;

import com.YomiOluwadara.conferencedemo.UserController;
import com.YomiOluwadara.conferencedemo.model.User;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.hibernate.tool.schema.spi.ExceptionHandler;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UserControllerTest {
	@Nested
	@DisplayName("testing the controller methods in session controller")
	class UserServicesTests {

		@InjectMocks
		UserController userController;

		@Mock
		UserService userService;

		User user;
		private MockMvc mockMvc;

		/**
		 * needed for mockito API to initialize mocks and make them available in
		 * UserController and UserService classes where they will be used
		 */
		@BeforeEach
		void setup() throws Exception {

			MockitoAnnotations.initMocks(this);
			user = new User();
			UserController controller = new UserController(userService);

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
		@DisplayName("Find one user given the user id")
		void findOneUserTest() {
			user.setUserId(1000);
			user.setUserName("testUserName");
			user.setPassword("testPassword");
			user.setFirstName("testFirstName");
			user.setLastName("testLastName");
			user.setMiddleName("testMiddleName");
			user.setTitle("testTitle");
			user.setCompany("testCompany");
			user.setEmail("test@myemail.mail");
			user.setPassPhrase("testPassPhrase");
			user.setPhoneNumber("1234567689");

		/*
			anyLong() -I expect this stubbed method to be called with any long, Long or null, but I don't care about the
			exact value.
		 */
			when(userService.findOneUser(anyLong())).thenReturn(user);

			User userReturnedFromUserClass = userController.findOneUser(user.getUserId());
			assertNotNull(user.getUserId());

			/*
				assert that other User variable from the user service are also equal to the values returned to the
				user controller
			 */
			assertEquals(this.user.getUserId(), user.getUserId());



		}

	}
}
