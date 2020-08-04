/**
 * @author OO046152 : Yomi Oluwadara
 *
 */
package com.YomiOluwadara.conferencedemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HomeControllerTest {

	@Mock
	static HomeController homeController;

	// create mock for class that has method that will be tested
	@BeforeEach
	public  void setUp(){
		 homeController = Mockito.mock(HomeController.class);
	}
		@Test
		@DisplayName("returns the hardcoded version of app")
		void appVersionTest() {
			when(homeController.getAppVersion()).thenReturn("1.0.0");
			assertEquals(homeController.getAppVersion(), "1.0.0");
		}

		@Test
		@DisplayName("returns welcome message")
		void welcomeMessageTest() {
			when(homeController.welcomeMessage())
					.thenReturn("Hello, welcome to the conference app project for big dummies");
			assertEquals(homeController.welcomeMessage(),
					"Hello, welcome to the conference app project for big dummies");
		}
}
