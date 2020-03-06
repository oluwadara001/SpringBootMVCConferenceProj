/**
 * 
 */
package com.YomiOluwadara.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.models.Session;
import com.YomiOluwadara.repositories.SessionRepository;

/**
 * @author OO046152 :Yomi Oluwadara
 * @RestController: makes class responds to incoming and outgoing as JSON end
 *                  points
 * @RequestMapping: specifies the route path/url
 * @Autowired: Use spring to inject dependency(dependency injection) for (for
 *             SessionReposity interface) - creates an instance of
 *             SessionRpeposity, the instance will be used to called the CRUD
 *             method the interface now have access to due to "extends"
 * @PostMapping :
 * @PathVariable : Take input and inject it as method parameter
 */

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsContoller {

	@Autowired
	private SessionRepository sessionRepository;

	// method that returns a list of all sessions
	@GetMapping
	public List<Session> list() {
		return sessionRepository.findAll();
	}

	// method that finds the id of a session that is passed as parameter from the
	// user
	@GetMapping
	@RequestMapping("/{id}")
	public Session get(@PathVariable Long id) {
		return sessionRepository.getOne(id);
	}

	// method that creates /post a new session
	@PostMapping
	public Session create(@RequestBody final Session session) {
		return sessionRepository.saveAndFlush(session);

	}

}
