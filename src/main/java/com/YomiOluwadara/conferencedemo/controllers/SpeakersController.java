/**
 * @author OO046152 :Yomi Oluwadara
 * <p>
 * see meanings of annotations used in the SessionsController.java
 * SpeakerService speakerService; so you call the methods in that class
 * from the controller class
 * <p>
 * all speakers : http://localhost:5000/api/v1/speakers
 */
package com.YomiOluwadara.conferencedemo.controllers;

import java.util.List;

import com.sun.xml.bind.v2.TODO;
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


@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

	SpeakerService speakerService;

	public SpeakersController(SpeakerService speakerService) {
		this.speakerService = speakerService;
	}

	/**
	 * @return a list of all speakers
	 */
	@GetMapping
	public @ResponseBody
	List<Speaker> listAllSpeakers() {
		return speakerService.allSpeakers();
	}

	/**
	 * @param id speaker id needed to find the speaker
	 * @return Speaker
	 */
	@GetMapping
	@RequestMapping("/{id}")
	public Speaker findOneSpeaker(@PathVariable Long id) {
		return speakerService.findOnespeaker(id);
	}

	/**
	 * @param speaker speaker to be created
	 * @return a new speaker
	 */
	@PostMapping
	public Speaker createSpeaker(@RequestBody Speaker speaker) {
		return speakerService.addNewSpeaker(speaker);
	}

	/**
	 * @param id speaker id to be deleted
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteASpeaker(@PathVariable Long id) {
		speakerService.deleteOneSpeaker(id);
	}

	/**
	 * @param id      speaker id to be updated
	 * @param speaker speaker to be updated
	 * @return updated speaker information
	 */
	//TODO : test if the PUT operation generates another id for the user
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Speaker updateSpeaker(@PathVariable Long id, @RequestBody Speaker speaker) {
		return speakerService.updateSpeakerInfo(id, speaker);
	}

}
