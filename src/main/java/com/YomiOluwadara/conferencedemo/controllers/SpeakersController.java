/**
 * 
 */
package com.YomiOluwadara.conferencedemo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.conferencedemo.models.Speaker;
import com.YomiOluwadara.conferencedemo.services.SpeakerService;

/**
 * @author OO046152 :Yomi Oluwadara
 * 
 *         see meanings of annotations used in the SessionsController.java
 *         SpeakerService speakerService; so you call the methods in that class
 *         from the controller class
 * 
 * 			all speakers : http://localhost:5000/api/v1/speakers
 *
 */

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

	SpeakerService speakerService;

	public SpeakersController(SpeakerService speakerService) {
		this.speakerService = speakerService;
	}

	@GetMapping
	public @ResponseBody List<Speaker> list() {
		return speakerService.allSpeakers();
	}

	@GetMapping
	@RequestMapping("/{id}")
	public Speaker get(@PathVariable Long id) {
		// return speakerDAO.getOne(id);
		return speakerService.findOnespeaker(id);
	}

	@PostMapping
	public Speaker create(@RequestBody Speaker speaker) {
		// return speakerDAO.saveAndFlush(speaker);
		return speakerService.addNewSpeaker(speaker);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		speakerService.deleteOneSpeaker(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		return speakerService.updateSpeakerInfo(id, speaker);
	}

}
