package com.YomiOluwadara.conferencedemo.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.services.SessionDetailsService;
import com.YomiOluwadara.conferencedemo.services.SessionService;
import com.YomiOluwadara.conferencedemo.services.SpeakerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/sessions")
public class SessionsController {
    private final SessionService sessionService;
    private final SpeakerService speakerService;
    private final SessionDetailsService sessionDetailsService;

    public SessionsController(SessionService sessionService, 
                            SpeakerService speakerService,
                            SessionDetailsService sessionDetailsService) {
        this.sessionService = sessionService;
        this.speakerService = speakerService;
        this.sessionDetailsService = sessionDetailsService;
    }

    @GetMapping
    public String listSessions(Model model, 
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String filter) {
        
        List<Session> sessions;
        
        // Load sessions with speakers for better performance
        if (keyword != null && !keyword.isEmpty()) {
            // Search by keyword
            sessions = sessionService.searchByKeyword(keyword);
        } else if (filter != null && !filter.isEmpty()) {
            // Apply filters
            switch (filter) {
                case "today":
                    LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
                    sessions = sessionService.findByDate(today);
                    break;
                case "upcoming":
                    sessions = sessionService.findUpcomingSessions();
                    break;
                default:
                    // Default sort by start time
                    sessions = sessionService.findAllWithSpeakers(Sort.by(Sort.Direction.ASC, "startTime"));
            }
        } else {
            // No filters or search, just get all sessions with speakers
            sessions = sessionService.findAllWithSpeakers(Sort.by(Sort.Direction.ASC, "startTime"));
        }
        
        model.addAttribute("sessions", sessions);
        return "sessions/list";
    }

    @GetMapping("/new")
    public String newSession(Model model) {
        model.addAttribute("session", new Session());
        model.addAttribute("speakers", speakerService.findAll());
        return "sessions/form";
    }

    @PostMapping
    public String createSession(@Valid @ModelAttribute("session") Session session, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("speakers", speakerService.findAll());
            return "sessions/form";
        }
        sessionService.save(session);
        return "redirect:/sessions";
    }

    @GetMapping("/{id}")
    public String viewSession(@PathVariable Long id, Model model) {
        Session session = sessionService.findById(id);
        model.addAttribute("session", session);
        
        // Add session details if they exist
        sessionDetailsService.findBySessionId(id)
            .ifPresent(details -> model.addAttribute("sessionDetails", details));
        
        // Add success and error message attributes if they exist in the model
        if (model.containsAttribute("success")) {
            model.addAttribute("successMessage", model.getAttribute("success"));
        }
        if (model.containsAttribute("error")) {
            model.addAttribute("errorMessage", model.getAttribute("error"));
        }
        
        return "sessions/view";
    }

    @GetMapping("/{id}/edit")
    public String editSession(@PathVariable Long id, Model model) {
        model.addAttribute("session", sessionService.findById(id));
        model.addAttribute("speakers", speakerService.findAll());
        return "sessions/form";
    }

    @PostMapping("/{id}")
    public String updateSession(@PathVariable Long id, @Valid @ModelAttribute("session") Session session, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("speakers", speakerService.findAll());
            return "sessions/form";
        }
        session.setId(id);
        sessionService.save(session);
        return "redirect:/sessions";
    }

    @GetMapping("/{id}/delete")
    public String deleteSessionConfirm(@PathVariable Long id) {
        sessionService.deleteById(id);
        return "redirect:/sessions";
    }

    // API Endpoints
    @GetMapping("/api")
    @ResponseBody
    public List<Session> listSessionsApi() {
        return sessionService.findAllWithSpeakers();
    }
    
    @GetMapping("/api/search")
    @ResponseBody
    public List<Session> searchSessionsApi(@RequestParam String keyword) {
        return sessionService.searchByKeyword(keyword);
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Session getSessionApi(@PathVariable Long id) {
        return sessionService.findById(id);
    }
    
    @GetMapping("/api/speaker/{speakerId}")
    @ResponseBody
    public List<Session> getSessionsBySpeakerApi(@PathVariable Long speakerId) {
        return sessionService.findBySpeakerId(speakerId);
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        List<Session> sessions = sessionService.findAllWithSpeakers(Sort.by(Sort.Direction.ASC, "startTime"));
        model.addAttribute("sessions", sessions);
        
        // Add success and error message attributes if they exist in the session
        if (model.containsAttribute("success")) {
            model.addAttribute("successMessage", model.getAttribute("success"));
        }
        if (model.containsAttribute("error")) {
            model.addAttribute("errorMessage", model.getAttribute("error"));
        }
        
        return "sessions/register";
    }
}