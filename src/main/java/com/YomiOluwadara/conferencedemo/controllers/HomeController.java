/**
 * 
 */
package com.YomiOluwadara.conferencedemo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.conferencedemo.services.HomeService;

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
 * 
 *        HEROKU link: https://yomi-conferenc-eapp.herokuapp.com/
 * 
 */

@RestController
public class HomeController {
	
	HomeService homeService;
	
	public HomeController (HomeService homeService) {
		this.homeService = homeService;
	}
	
	@GetMapping
	@RequestMapping("/home")
	public  @ResponseBody  String welcomeMessage() {
		return homeService.welcomeMessage();
	}
		
	@GetMapping
	@RequestMapping("/") // http://localhost:5000
	public @ResponseBody  String getAppVersion() {
		return homeService.appVersion();
		
	}

	
	

	
	

}
