/**
 * @author OO046152 :Yomi Oluwadara
 * <p>
 * class SessionsContoller : consists of all the CRUD operations that
 * could be performed on session
 * @RestController: makes class responds to incoming and outgoing as JSON end
 * points
 * @RequestMapping: specifies the route path/url
 * @Autowired: Use by spring to inject dependency(dependency injection) for (for
 * SessionReposity interface) - creates an instance of
 * SessionRpeposity, the instance will be used to called the CRUD
 * method the interface now have access to due to "extends"
 * <p>
 * url for all sessions: http://localhost:8080/sessions
 * url for fetching a session id of 2 : http://localhost:8080/sessions/2
 * url for creating new session : http://localhost:8080/sessions/new
 */
package com.YomiOluwadara.conferencedemo;

import com.YomiOluwadara.conferencedemo.controller.SessionsService;
import com.YomiOluwadara.conferencedemo.dao.SpeakerDAO;
import com.YomiOluwadara.conferencedemo.model.Session;
import com.YomiOluwadara.conferencedemo.model.Speaker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/sessions")
public class SessionsController {

	private final SessionsService sessionsService;
	private final SpeakerDAO speakerDAO;

	public SessionsController(SessionsService sessionsService, SpeakerDAO speakerDAO) {
		this.sessionsService = sessionsService;
		this.speakerDAO = speakerDAO;
	}

	/**
	 * Display list of all sessions
	 * @param model Spring Model to add attributes
	 * @return The sessions list view
	 */
	@GetMapping
	public String listAllSessions(Model model) {
		List<Session> sessions = sessionsService.allSessions();
		model.addAttribute("sessions", sessions);
		return "sessions/list";
	}

	/**
	 * Show form to create a new session
	 * @param model Spring Model to add attributes
	 * @return The session form view
	 */
	@GetMapping("/new")
	public String showNewSessionForm(Model model) {
		model.addAttribute("session", new Session());
		model.addAttribute("speakers", speakerDAO.findAll());
		return "sessions/form";
	}

	/**
	 * Show form to edit an existing session
	 * @param id The session ID to edit
	 * @param model Spring Model to add attributes
	 * @return The session form view
	 */
	@GetMapping("/{id}/edit")
	public String showEditSessionForm(@PathVariable Long id, Model model) {
		Session session = sessionsService.findOneSession(id);
		model.addAttribute("session", session);
		model.addAttribute("speakers", speakerDAO.findAll());
		return "sessions/form";
	}

	/**
	 * Display details of a specific session
	 * @param id The session ID to view
	 * @param model Spring Model to add attributes
	 * @return The session detail view
	 */
	@GetMapping("/{id}")
	public String getSessionDetails(@PathVariable Long id, Model model) {
		Session session = sessionsService.findOneSession(id);
		model.addAttribute("session", session);
		return "sessions/detail";
	}

	/**
	 * Save a new session
	 * @param session The session to save
	 * @param result Binding result for validation
	 * @param redirectAttributes For flash messages
	 * @return Redirect to sessions list
	 */
	@PostMapping("/save")
	public String saveSession(@ModelAttribute("session") Session session,
							  BindingResult result,
							  RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "sessions/form";
		}
		
		sessionsService.addNewSession(session);
		redirectAttributes.addFlashAttribute("successMessage", "Session created successfully!");
		return "redirect:/sessions";
	}

	/**
	 * Update an existing session
	 * @param session The session to update
	 * @param result Binding result for validation
	 * @param redirectAttributes For flash messages
	 * @return Redirect to sessions list
	 */
	@PostMapping("/update")
	public String updateSession(@ModelAttribute("session") Session session,
								BindingResult result,
								RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "sessions/form";
		}
		
		sessionsService.updateSessionInfo(session.getSessionId(), session);
		redirectAttributes.addFlashAttribute("successMessage", "Session updated successfully!");
		return "redirect:/sessions";
	}

	/**
	 * Delete a session
	 * @param id The session ID to delete
	 * @param redirectAttributes For flash messages
	 * @return Redirect to sessions list
	 */
	@GetMapping("/{id}/delete")
	public String deleteSession(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			sessionsService.deleteOneSession(id);
			redirectAttributes.addFlashAttribute("successMessage", "Session deleted successfully!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error deleting session: " + e.getMessage());
		}
		return "redirect:/sessions";
	}

	// API Endpoints
	
	/**
	 * API: Get all sessions
	 * @return List of all sessions
	 */
	@GetMapping("/api")
	@ResponseBody
	public List<Session> apiListAllSessions() {
		return sessionsService.allSessions();
	}

	/**
	 * API: Get one session by ID
	 * @param id The session ID
	 * @return The session
	 */
	@GetMapping("/api/{id}")
	@ResponseBody
	public Session apiGetOneSession(@PathVariable Long id) {
		return sessionsService.findOneSession(id);
	}

	/**
	 * API: Create a new session
	 * @param session The session to create
	 * @return The created session
	 */
	@PostMapping("/api")
	@ResponseBody
	public Session apiCreateSession(@RequestBody Session session) {
		return sessionsService.addNewSession(session);
	}

	/**
	 * API: Update a session
	 * @param id The session ID to update
	 * @param session The updated session data
	 * @return The updated session
	 */
	@PutMapping("/api/{id}")
	@ResponseBody
	public Session apiUpdateSession(@PathVariable Long id, @RequestBody Session session) {
		return sessionsService.updateSessionInfo(id, session);
	}

	/**
	 * API: Delete a session
	 * @param id The session ID to delete
	 */
	@DeleteMapping("/api/{id}")
	@ResponseBody
	public void apiDeleteSession(@PathVariable Long id) {
		sessionsService.deleteOneSession(id);
	}
}
