/**
 * 
 */
package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.repositories.SessionDAO;

/**
 * @author OO046152   : Yomi Oluwadara
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
	
	

}
