/**
 * 
 */
package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.repositories.SessionDAO;

/**
 * @author OO046152   : Yomi Oluwadara
 * 
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
 *
 */

@Service
public class SessionsService {
	
	@Autowired
	private SessionDAO sessionDAO;
	
	// method that returns a list of all sessions
	public List<Session> list() {
		return sessionDAO.findAll();
	}
	
	// method that finds the id of a session that is passed as parameter from the
	// id is being passed when the method is called in the session controller class
	public Session get(@PathVariable Long id) {
		return sessionDAO.getOne(id);
	}
	
	// method that creates/ post a new session 
	public Session create(@RequestBody final Session session) {
		return sessionDAO.saveAndFlush(session);
	}
	
	
	// method that deletes a session given its id, provided no children sessions
	// return method is void because record is being deleted
	
	public void delete(@PathVariable Long id) {
		sessionDAO.deleteById(id);
		// TODO : add logic that allows for the deleting of children records
	}
	
	// method that updates a specified record find a session given its id,then store that id in Session variable (session)
	//@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Session update(@PathVariable Long id, @RequestBody Session session) {
		// taking the existing session and copies in incoming session data to it but
		Session existingSession = sessionDAO.getOne(id);
		// implementation of: BeanUtils.copyProperties(source, target,
		// ignoreProperties);
		BeanUtils.copyProperties(session, existingSession, "session_id");
		// ignoringProperties (the session_id)
		// ADD LOGIC to ensure all attributes of session are passed in( else they will
		// be defaulted to null),otherwise return a 400 bad payload
		return sessionDAO.saveAndFlush(existingSession);
	}
	
	
	
	
	
	

}
