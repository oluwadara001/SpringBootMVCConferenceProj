/**
 * 
 */
package com.YomiOluwadara.conferencedemo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OO046152 :Yomi Oluwadara
 * 
 *         This is eventually serve as the landing page for the application.
 * 
 * @Value : Injects the hard-coded value of the yomi.app.version from the
 *        application properties to the variable to which its annotated
 * 
 *        Method getStatus() uses a map to put the coded version of application
 *        from the application.properties file
 */

@RestController
public class HomeController {

	@Value("${yomi.app.version}")
	private String yomiAppVersion;

	@GetMapping
	@RequestMapping("/") // http://localhost:5000
	public Map getStatus() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("yomi.app.version", yomiAppVersion);
		return map;
	}

}
