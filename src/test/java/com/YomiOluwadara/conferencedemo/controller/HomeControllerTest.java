/**
 * @author OO046152 : Yomi Oluwadara
 *
 */
package com.YomiOluwadara.conferencedemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.YomiOluwadara.conferencedemo.HomeController;
import com.YomiOluwadara.conferencedemo.services.HomeService;

class HomeControllerTest {

	@Mock
	private HomeService homeService;

	private HomeController homeController;

	@BeforeEach
	public void setUp() {
		homeService = Mockito.mock(HomeService.class);
		homeController = new HomeController(homeService, null, null, null);
	}

	@Test
	@DisplayName("returns the hardcoded version of app")
	void appVersionTest() {
		when(homeService.getAppVersion()).thenReturn("1.0.0");
		assertEquals(homeService.getAppVersion(), "1.0.0");
	}

	@Test
	@DisplayName("returns welcome message")
	void welcomeMessageTest() {
		when(homeService.welcomeMessage())
				.thenReturn("Hello, welcome to the conference app project for big dummies");
		assertEquals(homeService.welcomeMessage(),
				"Hello, welcome to the conference app project for big dummies");
	}
}
