/**
 * 
 */
package com.YomiOluwadara.conferencedemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.YomiOluwadara.conferencedemo.controllers.HomeController;

/**
 * @author OO046152
 * 
 * 
 *
 */

class HomeControllerTest {

	@Test
	@DisplayName("test should return the welcome message")
	void appVersionTest() {
		// create mock for class that has method that will be tested
		HomeController homeController = mock(HomeController.class);
		// define return value for method getAppVersion() using when().. thenReturn
		// Mockto method
		when(homeController.getAppVersion()).thenReturn("1.0.0");
		// use mock in test....assrtEqaul(Expected,actual)
		assertEquals(homeController.getAppVersion(), "1.0.0");

	}

}
