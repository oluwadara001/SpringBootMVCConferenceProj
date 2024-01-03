/**
 * @author OO046152
 * creating a class member of AttendeeService so will can call the
 * methods in that class inside this class- controller class
 * url for retreive all registered attendees: http://localhost:8080/home/attendees
 */

package com.YomiOluwadara.conferencedemo;

import com.YomiOluwadara.conferencedemo.controller.AttendeeService;
import com.YomiOluwadara.conferencedemo.model.Attendee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //Need this for returning a web view
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
    @RequestMapping(value = "/attendees")
    String listAllAttendees(Model model) {
        List<Attendee> attendees = attendeeService.allAttendees();
        //attendee object is passed to the view using the model
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

//    @RequestMapping(value = "/attendees/id")
//    //public @ResponseBody
//    String listAttendeeById(@PathVariable Long id, Model model) {
//        Attendee attendee = attendeeService.findOneAttendee(id);
//        model.addAttribute("attendees", attendee);
//        return "attendees";
//        //return attendeeService.findOneAttendee(id);
//    }

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
     * @param id       attendee id that will be updated
     * @param attendee attendee that would be updates
     * @return the updated information of the attendee
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Attendee updateAttendeeInfo(@PathVariable Long id, Attendee attendee) {
        return attendeeService.updateAttendeeInfo(id, attendee);
    }
}
