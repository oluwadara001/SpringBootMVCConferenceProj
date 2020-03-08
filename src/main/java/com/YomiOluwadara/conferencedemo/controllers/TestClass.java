/**
 * 
 */
package com.YomiOluwadara.conferencedemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OO046152 This is only serve as a test controller for errors-404...
 */
@RestController

public class TestClass {

	@RequestMapping("/api/v1/test")
	public String test() {
		return "Yay! I did not get caught be error 404";
	}
}
