/**
 * 
 */
package com.YomiOluwadara.conferencedemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.YomiOluwadara.conferencedemo.controllers.HomeController;

/**
 * @author OO046152 : Yomi Oluwadara
 * 
 */

class HomeServicesTest {
	@DisplayName("Tests in the Home controller class")
	@Nested
	class HomeControllerTests {
		@Test
		@DisplayName("returns the hardcoded version of app")
		void appVersionTest() {
			// create mock for class that has method that will be tested
			HomeController homeController = mock(HomeController.class);
			// define return value for method getAppVersion() using when().. thenReturn
			// Mocikto method
			when(homeController.getAppVersion()).thenReturn("1.0.0");
			// use mock in test....assrtEqaul(Expected,actual)
			assertEquals(homeController.getAppVersion(), "1.0.0");

		}

		@Test
		@DisplayName("returns welcome message")
		void welcomeMessageTest() {
			HomeController homeController = mock(HomeController.class);
			when(homeController.welcomeMessage())
					.thenReturn("Hello, welcome to the conference app project for big dummies");
			assertEquals(homeController.welcomeMessage(),
					"Hello, welcome to the conference app project for big dummies");

		}

	}

}
