/**
 * @author OO046152 : Yomi Oluwadara
 * @PostMapping :
 * @PathVariable : Take input and inject it as method parameter method =
 * RequestMethod.DELETE (htpp delete) is needed if the operation
 * is not a GET or POST
 * <p>
 * saveAndFlush: save changes and commit them to the database
 * <p>
 * BeansUtils object:takes the existing session and copies in
 * incoming session data to it
 * <p>
 * ignoreProperties: tells the BeansUtils what
 * properties/variables that wont be updated/replaced
 * <p>
 * all CRUD methods implemented
 */

package com.YomiOluwadara.conferencedemo.services;

import com.YomiOluwadara.conferencedemo.dao.SessionDAO;
import com.YomiOluwadara.conferencedemo.model.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class SessionsService {

	@Autowired
	private SessionDAO sessionDAO;

	/**
	 * @return a list of conference sessions
	 */
	public List<Session> allSessions() {
		return sessionDAO.findAll();
	}

	/**
	 * @param id session id to be returned
	 * @return a session based on the id
	 */
	public Session findOneSession(@PathVariable Long id) {
		return sessionDAO.getOne(id);
	}

	/**
	 * @param session to be added
	 * @return newly added session
	 */
	public Session addNewSession(@RequestBody final Session session) {
		return sessionDAO.saveAndFlush(session);
	}

	/**
	 * @param id session id to be deleted
	 */
	public String deleteOneSession(@PathVariable Long id) {
		// TODO : add logic that allows for the deleting of children records
		sessionDAO.deleteById(id);
		return "Deletion was successful";
	}

	/**
	 * @param id session id to be updated
	 * @param session to be updated
	 * @return updated session
	 */
	public Session updateSessionInfo(@PathVariable Long id, @RequestBody Session session) {
		// taking the existing session and copies in incoming session data to it but
		Session existingSession = sessionDAO.getOne(id);
		BeanUtils.copyProperties(session, existingSession, "session_id");
		// TODO ADD LOGIC to ensure all attributes of session are passed in( else they will
		// be defaulted to null,otherwise return a 400 bad payload
		return sessionDAO.saveAndFlush(existingSession);
	}
}
