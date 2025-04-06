package com.YomiOluwadara.conferencedemo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YomiOluwadara.conferencedemo.models.Attendee;
import com.YomiOluwadara.conferencedemo.services.AttendeeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public String listAttendees(Model model) {
        model.addAttribute("attendees", attendeeService.findAll());
        return "attendees/list";
    }

    @GetMapping("/new")
    public String newAttendee(Model model) {
        model.addAttribute("attendee", new Attendee());
        return "attendees/form";
    }

    @PostMapping
    public String createAttendee(@Valid @ModelAttribute("attendee") Attendee attendee, BindingResult result) {
        if (result.hasErrors()) {
            return "attendees/form";
        }
        attendeeService.save(attendee);
        return "redirect:/attendees";
    }

    @GetMapping("/{id}")
    public String viewAttendee(@PathVariable Long id, Model model) {
        model.addAttribute("attendee", attendeeService.findById(id));
        return "attendees/view";
    }

    @GetMapping("/{id}/edit")
    public String editAttendee(@PathVariable Long id, Model model) {
        model.addAttribute("attendee", attendeeService.findById(id));
        return "attendees/form";
    }

    @PostMapping("/{id}")
    public String updateAttendee(@PathVariable Long id, @Valid @ModelAttribute("attendee") Attendee attendee, BindingResult result) {
        if (result.hasErrors()) {
            return "attendees/form";
        }
        attendee.setId(id);
        attendeeService.save(attendee);
        return "redirect:/attendees";
    }

    @GetMapping("/{id}/delete")
    public String deleteAttendee(@PathVariable Long id) {
        attendeeService.deleteById(id);
        return "redirect:/attendees";
    }

    // API Endpoints
    @GetMapping("/api")
    @ResponseBody
    public List<Attendee> listAttendeesApi() {
        return attendeeService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Attendee getAttendeeApi(@PathVariable Long id) {
        return attendeeService.findById(id);
    }
} 