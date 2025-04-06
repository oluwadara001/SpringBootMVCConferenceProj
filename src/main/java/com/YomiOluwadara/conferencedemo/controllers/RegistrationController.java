package com.YomiOluwadara.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.models.User;
import com.YomiOluwadara.conferencedemo.services.SessionService;
import com.YomiOluwadara.conferencedemo.services.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @GetMapping("/session/{id}")
    public String showRegistrationForm(@PathVariable Long id, Model model) {
        Session session = sessionService.findById(id);
        if (session == null) {
            return "redirect:/sessions";
        }
        model.addAttribute("sessionDetails", session);
        return "registration/form";
    }

    @PostMapping("/session/{id}")
    public String register(@PathVariable Long id,
                         @RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam(required = false) String middleName,
                         @RequestParam(required = false) String title,
                         @RequestParam String email,
                         @RequestParam(required = false) String company,
                         @RequestParam(required = false) String phoneNumber,
                         @RequestParam String password,
                         @RequestParam String passphrase,
                         @RequestParam(required = false) Boolean terms,
                         RedirectAttributes redirectAttributes) {
        
        // Validate required fields
        if (firstName == null || firstName.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "First name is required");
            return "redirect:/register/session/" + id;
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Last name is required");
            return "redirect:/register/session/" + id;
        }
        if (email == null || email.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Email is required");
            return "redirect:/register/session/" + id;
        }
        if (password == null || password.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Password is required");
            return "redirect:/register/session/" + id;
        }
        if (passphrase == null || passphrase.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Passphrase is required");
            return "redirect:/register/session/" + id;
        }
        if (terms == null || !terms) {
            redirectAttributes.addFlashAttribute("error", "You must agree to the terms and conditions");
            return "redirect:/register/session/" + id;
        }

        Session session = sessionService.findById(id);
        if (session == null) {
            redirectAttributes.addFlashAttribute("error", "Session not found");
            return "redirect:/sessions";
        }

        try {
            // Check if user with this email already exists
            if (userService.findByEmail(email.trim()) != null) {
                redirectAttributes.addFlashAttribute("error", "A user with this email already exists");
                return "redirect:/register/session/" + id;
            }

            // Create new user
            User user = new User();
            user.setFirstName(firstName.trim());
            user.setLastName(lastName.trim());
            user.setMiddleName(middleName != null ? middleName.trim() : null);
            user.setTitle(title != null ? title.trim() : null);
            user.setEmail(email.trim());
            user.setCompany(company != null ? company.trim() : null);
            user.setPhoneNumber(phoneNumber != null ? phoneNumber.trim() : null);
            user.setPassword(password); // Note: In production, this should be encrypted
            user.setPassphrase(passphrase); // Note: In production, this should be encrypted
            user.setUsername(email.trim()); // Using email as username
            user.setEnabled(true);

            // Save user
            user = userService.save(user);

            // Register user for session
            sessionService.registerUser(session.getId(), user.getId());
            
            // Add success message and session details to redirect attributes
            redirectAttributes.addFlashAttribute("success", "Registration successful! Welcome to " + session.getTitle());
            redirectAttributes.addFlashAttribute("sessionId", session.getId());
            redirectAttributes.addFlashAttribute("sessionTitle", session.getTitle());
            
            // Redirect to success page
            return "redirect:/register/success";
        } catch (Exception e) {
            String errorMessage = "Registration failed: ";
            if (e.getMessage() != null && e.getMessage().contains("duplicate key value")) {
                errorMessage += "A user with this email already exists";
            } else {
                errorMessage += e.getMessage();
            }
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/register/session/" + id;
        }
    }

    @PostMapping("/session/{sessionId}/cancel")
    public String cancelRegistration(@PathVariable Long sessionId, RedirectAttributes redirectAttributes) {
        try {
            // For now, we'll use a hardcoded user ID
            Long userId = 1L;
            sessionService.unregisterUser(sessionId, userId);
            redirectAttributes.addFlashAttribute("success", "Registration cancelled successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/sessions/" + sessionId;
    }

    @GetMapping("/session")
    public String showRegistrationHome(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Please select a session to register for");
        return "redirect:/sessions/register";
    }

    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        // The success message and session details will be available as flash attributes
        return "registration/success";
    }
} 