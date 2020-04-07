/**
 * 
 */
package com.YomiOluwadara.conferencedemo.services;

import org.springframework.stereotype.Service;

/**
 * @author OO046152
 *
 *This class contains the services related to "home"
 */

@Service
public class HomeService {
	
	public String welcomeMessage() {
		return "Hello, welcome to the conference app project for big dummies";
	}
	
	public String appVersion() {
		return "1.0.0";
	}
	
	
	/*
	 * implementing application version using maps.
	 * 
	 * 
	 * @Value("${yomi.app.version}")
	private String yomiAppVersion;

	@GetMapping
	@RequestMapping("/") // http://localhost:5000
	public Map getVersion() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("yomi.app.version", yomiAppVersion);
		return map;
	}

	 * 
	 */
	
}
