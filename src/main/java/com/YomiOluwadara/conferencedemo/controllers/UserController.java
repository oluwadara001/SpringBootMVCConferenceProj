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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YomiOluwadara.conferencedemo.models.User;
import com.YomiOluwadara.conferencedemo.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model, @RequestParam(required = false) String keyword, 
                          @RequestParam(required = false) String filter) {
        List<User> users;
        
        if (keyword != null && !keyword.isEmpty()) {
            // In a real application, you would implement search functionality in the service
            users = userService.findAll();
        } else if (filter != null && !filter.isEmpty()) {
            // In a real application, you would implement filtering in the service
            users = userService.findAll();
        } else {
            users = userService.findAll();
        }
        
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/form";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/view";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/form";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/form";
        }
        user.setId(id);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    // API Endpoints
    @GetMapping("/api")
    @ResponseBody
    public List<User> listUsersApi() {
        return userService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public User getUserApi(@PathVariable Long id) {
        return userService.findById(id);
    }
} 