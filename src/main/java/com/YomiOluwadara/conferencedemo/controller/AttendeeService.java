
/**
 * @author OO046152
 * <p>
 * comments for code: create a member of the AttendeeDAO so you can
 * access the CRUD methods in the JPA repository
 */

package com.YomiOluwadara.conferencedemo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.YomiOluwadara.conferencedemo.model.Attendee;
import com.YomiOluwadara.conferencedemo.dao.AttendeeDAO;

@Service
public class AttendeeService {

	@Autowired
	AttendeeDAO attendeeDAO;

	/**
	 * @return the list of all attendee
	 */
	public List<Attendee> allAttendees() {
		return attendeeDAO.findAll();
	}

	/**
	 * @param id attendee id to be fetched from all attendees
	 * @return one attendee
	 */
	public Attendee findOneAttendee(@PathVariable long id) {
		return attendeeDAO.getOne(id);
	}

	/**
	 * @param attendee to be added
	 * @return newly added attendee
	 */
	public Attendee addNewAttendee(@RequestBody Attendee attendee) {
		return attendeeDAO.saveAndFlush(attendee);
	}

	/**
	 * @param id attendee id to be deleted
	 */
	public void deleteOneAttendee(@PathVariable Long id) {
		// TODO :ADD LOGIC to check for children method before deleting
		attendeeDAO.deleteById(id);
	}

	/**
	 * @param id       attendee id to be updated
	 * @param attendee to be updated
	 * @return updated attendee
	 */
	public Attendee updateAttendeeInfo(@PathVariable Long id, Attendee attendee) {
		// get the existing attending by its id
		Attendee existingAttendee = attendeeDAO.getOne(id);
		// could overlay existing attendee info ,with the exception of the attendee id
		BeanUtils.copyProperties(attendee, existingAttendee, "attendee_id");
		return attendeeDAO.saveAndFlush(existingAttendee);
	}

}
