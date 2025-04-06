package com.YomiOluwadara.conferencedemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YomiOluwadara.conferencedemo.models.Speaker;
import com.YomiOluwadara.conferencedemo.repositories.SpeakerProfileRepository;
import com.YomiOluwadara.conferencedemo.services.SpeakerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/speakers")
public class SpeakersController {
    private final SpeakerService speakerService;
    
    @Autowired
    private SpeakerProfileRepository speakerProfileRepository;

    public SpeakersController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping
    public String listSpeakers(Model model) {
        model.addAttribute("speakers", speakerService.findAll());
        return "speakers/list";
    }

    @GetMapping("/new")
    public String newSpeaker(Model model) {
        model.addAttribute("speaker", new Speaker());
        return "speakers/form";
    }

    @PostMapping
    public String createSpeaker(@Valid @ModelAttribute("speaker") Speaker speaker, BindingResult result) {
        if (result.hasErrors()) {
            return "speakers/form";
        }
        speakerService.save(speaker);
        return "redirect:/speakers";
    }

    @GetMapping("/{id}")
    public String viewSpeaker(@PathVariable Long id, Model model) {
        var speaker = speakerService.findById(id);
        if (speaker == null) {
            return "redirect:/speakers";
        }
        model.addAttribute("speaker", speaker);
        return "speakers/profile";
    }

    @GetMapping("/{id}/edit")
    public String editSpeaker(@PathVariable Long id, Model model) {
        model.addAttribute("speaker", speakerService.findById(id));
        return "speakers/form";
    }

    @PostMapping("/{id}")
    public String updateSpeaker(@PathVariable Long id, @Valid @ModelAttribute("speaker") Speaker speaker, BindingResult result) {
        if (result.hasErrors()) {
            return "speakers/form";
        }
        speaker.setId(id);
        speakerService.save(speaker);
        return "redirect:/speakers";
    }

    @DeleteMapping("/{id}")
    public String deleteSpeaker(@PathVariable Long id) {
        speakerService.deleteById(id);
        return "redirect:/speakers";
    }

    // API Endpoints
    @GetMapping("/api")
    @ResponseBody
    public List<Speaker> listSpeakersApi() {
        return speakerService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Speaker getSpeakerApi(@PathVariable Long id) {
        return speakerService.findById(id);
    }
} 