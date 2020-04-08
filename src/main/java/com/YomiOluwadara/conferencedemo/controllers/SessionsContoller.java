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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.repositories.SessionDAO;
import com.YomiOluwadara.conferencedemo.services.SessionsService;

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
 * 
 */

@RestController
@RequestMapping("/api/v1/sessions")

public class SessionsContoller {
	
	SessionsService sessionsService;
	
	public SessionsContoller (SessionsService sessionsService) {
		this.sessionsService = sessionsService;
	}
	

	@Autowired
	 SessionDAO sessionDAO;

	
	@GetMapping
	public @ResponseBody List<Session> list() {
		return sessionsService.list();	
	}

	
	
	@GetMapping
	@RequestMapping("/{id}") //url for fetching a session id  of 2 : http://localhost:5000/api/v1/sessions/2
	public @ResponseBody Session get(@PathVariable Long id) {
		return sessionsService.get(id);
	}

	// method that creates/ post a new session 
	@PostMapping
	public Session create(@RequestBody final Session session) {
		return sessionsService.create(session);
	}
	
	
	// method that deletes a session given its id, provided no children sessions
	// return method is void because record is being deleted
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		sessionsService.delete(id);
	}
	

	 //method that updates a specified record find a session given its id,then store that id in Session variable (session)
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody Session update(@PathVariable Long id, @RequestBody Session session) {
		return sessionsService.update(id, session);
	}
	
	
}
