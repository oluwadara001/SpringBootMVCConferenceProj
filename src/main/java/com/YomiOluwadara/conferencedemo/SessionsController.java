/**
 * @author OO046152 :Yomi Oluwadara
 * <p>
 * class SessionsContoller : consists of all the CRUD operations that
 * could be performed on session
 * @RestController: makes class responds to incoming and outgoing as JSON end
 * points
 * @RequestMapping: specifies the route path/url
 * @Autowired: Use by spring to inject dependency(dependency injection) for (for
 * SessionReposity interface) - creates an instance of
 * SessionRpeposity, the instance will be used to called the CRUD
 * method the interface now have access to due to "extends"
 * <p>
 * url for all sessions: http://localhost:5000/api/v1/sessions/
 * url for fetching a session id of 2 : http://localhost:5000/api/v1/sessions/2
 * url for creating new session :  http://localhost:5000/api/v1/sessions/ (POST request needing no session id passed in)
 */
package com.YomiOluwadara.conferencedemo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.conferencedemo.model.Session;
import com.YomiOluwadara.conferencedemo.controller.SessionsService;


@RestController
@RequestMapping("/api/v1/sessions")

public class SessionsController {

	SessionsService sessionsService;

	public SessionsController(SessionsService sessionsService) {
		this.sessionsService = sessionsService;
	}


	/**
	 * @return a list of all sessions in the database
	 */
	@GetMapping
	public @ResponseBody
	List<Session> listAllSessions() {
		return sessionsService.allSessions();
	}

	/**
	 * @param id session id needed to return information about a session
	 * @return a specific session from the database
	 */
	@GetMapping
	@RequestMapping("/{id}")
	public @ResponseBody
	Session getOneSession(@PathVariable long id) {
		return sessionsService.findOneSession(id);
	}


	/**
	 * @param session session that needs to be created
	 * @return newly created session
	 */
	@PostMapping
	public Session createNewSession(@RequestBody final Session session) {
		return sessionsService.addNewSession(session);
	}

	/**
	 *  method that deletes a session given its id, provided no children sessions
	 * 	return method is void because record is being deleted
	 * @param id id of session that needs to be deleted
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	void deleteOneSession(@PathVariable Long id) {
		sessionsService.deleteOneSession(id);
	}

	/**
	 * method that updates a specified record find a session given its id,then stores
	 * that id in Session variable (session)
	 *
	 * @param id      session id to be updated
	 * @param session specific session that will be updated
	 * @return updated session
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Session updateSession(@PathVariable Long id, @RequestBody Session session) {
		return sessionsService.updateSessionInfo(id, session);
	}
}
