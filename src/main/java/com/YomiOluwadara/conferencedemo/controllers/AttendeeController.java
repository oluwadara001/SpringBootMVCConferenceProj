/**
 *
 */
package com.YomiOluwadara.conferencedemo.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.conferencedemo.models.Attendee;
import com.YomiOluwadara.conferencedemo.services.AttendeeService;

/**
 * @author OO046152
 *
 *         creating a class member of AttendeeService so will can call the
 *         methods in that class inside this class- controller class
 *
 */

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

	AttendeeService attendeeService;

	public AttendeeController(AttendeeService attendeeService) {
		this.attendeeService = attendeeService;
	}

	@GetMapping
	public @ResponseBody
	List<Attendee> list() {
		return attendeeService.allAttendees();
	}

	@GetMapping
	@RequestMapping("/{id}")
	public @ResponseBody
	Attendee get(@PathVariable Long id) {
		return attendeeService.findOneAttendee(id);
	}

	@PostMapping
	public Attendee create(Attendee attendee) {
		return attendeeService.addNewAttendee(attendee);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		attendeeService.deleteOneAttendee(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Attendee update(@PathVariable Long id, Attendee attendee) {
		return attendeeService.updateAttendeeInfo(id, attendee);
	}

}
