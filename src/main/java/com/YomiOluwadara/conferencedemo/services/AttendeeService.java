/**
 * 
 */
package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.YomiOluwadara.conferencedemo.models.Attendee;
import com.YomiOluwadara.conferencedemo.repositories.AttendeeDAO;

/**
 * @author OO046152
 * 
 *         comments for code: create a member of the AttendeeDAO so you can
 *         access the CRUD methods in the JPA repository
 *
 */

@Service
public class AttendeeService {

	@Autowired
	AttendeeDAO attendeeDAO;

	// method that returns the list of all attendee
	public List<Attendee> allAttendees() {
		return attendeeDAO.findAll();
	}

	// method that returns a specific speaker given their id
	public Attendee findOneAttendee(@PathVariable long id) {
		return attendeeDAO.getOne(id);
	}

	// Add a new attendee -Request all attributes of the attendee
	public Attendee addNewAttendee(@RequestBody Attendee attendee) {
		return attendeeDAO.saveAndFlush(attendee);
	}

	// method that deletes a speaker given their id
	public void deleteOneAttendee(@PathVariable Long id) {
		// ADD LOGIC to check for children method before deleting
		attendeeDAO.deleteById(id);
	}

	// method that updates/patch certain attributes of a speaker , takes in all the
	// attributes of the Attendee though
	public Attendee updateAttendeeInfo(@PathVariable Long id, Attendee attendee) {
		// get the existing attending by its id
		Attendee exstingAttendee = attendeeDAO.getOne(id);
		// could overlay existing attendee info ,with the exception of the attendee id
		BeanUtils.copyProperties(attendee, exstingAttendee, "attendee_id");
		return attendeeDAO.saveAndFlush(exstingAttendee);

	}

}
