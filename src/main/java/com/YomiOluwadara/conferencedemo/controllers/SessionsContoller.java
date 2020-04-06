/**
 * 
 */
package com.YomiOluwadara.conferencedemo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.repositories.SessionRepository;

/**
 * @author OO046152 :Yomi Oluwadara
 * 
 * class SessionsContoller : consists of all the CRUD operations that could be performed on session
 * 
 * @RestController: makes class responds to incoming and outgoing as JSON end
 *                  points
 * @RequestMapping: specifies the route path/url
 * @Autowired: Use by spring to inject dependency(dependency injection) for (for
 *             SessionReposity interface) - creates an instance of
 *             SessionRpeposity, the instance will be used to called the CRUD
 *             method the interface now have access to due to "extends"
 * @PostMapping :
 * @PathVariable : Take input and inject it as method parameter method =
 *               RequestMethod.DELETE (htpp delete) is needed if the operation
 *               is not a GET or POST
 * 
 *               saveAndFlush: save changes and commit them to the database
 * 
 *               BeansUtils object:takes the existing session and copies in
 *               incoming session data to it
 * 
 *               ignoreProperties: tells the BeansUtils what
 *               properties/variables that wont be updated/replaced
 * 
 *               all CRUD methods implemented
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
	@RequestMapping("/{id}") //url for fetching a session id  of 2 : http://localhost:5000/api/v1/sessions/2
	public Session get(@PathVariable Long id) {
		return sessionRepository.getOne(id);
	
		
	}

	// method that creates/ post a new session at http://localhost:5000/api/v1/sessions/ and passing ( as json)  all the variables needed to create a session object
	@PostMapping
	public Session create(@RequestBody final Session session) {
		return sessionRepository.saveAndFlush(session);
	}

	// method that deletes a session given its id, provided no children sessions
	// return method is void because record is being deleted
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		// ADD LOGIC to check for children method before deleting
		sessionRepository.deleteById(id);
	}

	// find a session given its id,then store that id in Session variable (session)

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Session update(@PathVariable Long id, @RequestBody Session session) {
		// taking the existing session and copies in incoming session data to it but
		Session existingSession = sessionRepository.getOne(id);
		// implementation of: BeanUtils.copyProperties(source, target,
		// ignoreProperties);
		BeanUtils.copyProperties(session, existingSession, "session_id");
		// ignoringProperties (the session_id)
		// ADD LOGIC to ensure all attributes of session are passed in( else they will
		// be defaulted to null),otherwise return a 400 bad payload
		return sessionRepository.saveAndFlush(existingSession);

	}
}
