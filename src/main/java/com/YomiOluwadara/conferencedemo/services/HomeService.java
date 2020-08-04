/**
 * @author OO046152
 * <p>
 * This class contains the services related to "home" /* implementing
 * application version using maps.
 * @GetMapping
 * @RequestMapping("/") // http://localhost:5000 public Map
 *
 */
package com.YomiOluwadara.conferencedemo.services;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

	/**
	 * @return the application welcome message
	 */
	public String welcomeMessage() {
		return "Hello, welcome to the conference app project for big dummies";
	}

	/**
	 * @return the app version
	 */
	public String appVersion() {
		return "1.0.0";
	}

}
