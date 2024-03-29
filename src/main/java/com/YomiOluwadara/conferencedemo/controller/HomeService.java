package com.YomiOluwadara.conferencedemo.controller;

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

	public String addUserDaoToCacheErrorMsg() {
		return "User is already in user cache";
	}

}
