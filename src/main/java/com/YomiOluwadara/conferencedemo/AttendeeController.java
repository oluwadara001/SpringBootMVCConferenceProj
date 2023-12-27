/**
 * @author OO046152
 * <p>
 * creating a class member of AttendeeService so will can call the
 * methods in that class inside this class- controller class
 * <p>
 * url for retreive all registered attendees: http://localhost:5000/api/v1/attendees/
 */

package com.YomiOluwadara.conferencedemo;

import com.YomiOluwadara.conferencedemo.controller.AttendeeService;
import com.YomiOluwadara.conferencedemo.model.Attendee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController // for returning json
@Controller //For returning a web view
//@RequestMapping("/api/v1/attendees")

//@RequestMapping("/attendees")

public class AttendeeController {

	AttendeeService attendeeService;

	/**
	 * @param attendeeService variable for class constructor
	 */
	public AttendeeController(AttendeeService attendeeService) {
		this.attendeeService = attendeeService;
	}

	/**
	 * @return a list of all attendees
	 */
//	@GetMapping
//	public @ResponseBody
//	List<Attendee> listAllAttendees() {
//		return attendeeService.allAttendees();
//	}


	@RequestMapping(value = "/attendees")
	String listAllAttendees(Model model) {
		List <Attendee> attendees = attendeeService.allAttendees();
		//attendee object os passed to the view
		model.addAttribute("attendees", attendees);
		return "attendees";
	}

	/**
	 * @param id id of a given attendee
	 * @return the record of the attendee that was passed in
	 */
	@GetMapping
	@RequestMapping("/{id}")
	public @ResponseBody
	Attendee listAttendeeById(@PathVariable Long id) {
		return attendeeService.findOneAttendee(id);
	}

	/**
	 * @param attendee an attendee that would be added
	 * @return the record of the newly added attendee
	 */
	@PostMapping
	public Attendee createNewAttendee(Attendee attendee) {
		return attendeeService.addNewAttendee(attendee);
	}

	/**
	 * @param id attendee id to be deleted
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteOneAttendee(@PathVariable Long id) {
		attendeeService.deleteOneAttendee(id);
	}

	/**
	 * @param id attendee id that will be updated
	 * @param attendee attendee that would be updates
	 * @return the updated information of the attendee
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Attendee updateAttendeeInfo(@PathVariable Long id, Attendee attendee) {
		return attendeeService.updateAttendeeInfo(id, attendee);
	}

}
