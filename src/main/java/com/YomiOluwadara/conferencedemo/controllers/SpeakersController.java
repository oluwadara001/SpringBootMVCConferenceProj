/**
 * 
 */
package com.YomiOluwadara.conferencedemo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.conferencedemo.models.Speaker;
import com.YomiOluwadara.conferencedemo.repositories.SpeakerRepository;

/**
 * @author OO046152 :Yomi Oluwadara
 * 
 *         see meanings of annotations used in the SessionsController.java
 *
 */

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

	@Autowired
	private SpeakerRepository speakerRepository;

	// method that returns the list of all speakers
	@GetMapping
	public List<Speaker> list() {
		return speakerRepository.findAll();
	}

	// method that returns a specific speaker given their id
	@GetMapping
	@RequestMapping("/{id}")
	public Speaker get(@PathVariable Long id) {
		return speakerRepository.getOne(id);
	}

	// creates a new speaker
	// http://localhost:8888/api/v1/speakers
	@PostMapping
	public Speaker create(@RequestBody Speaker speaker) {
		return speakerRepository.saveAndFlush(speaker);
	}

	// method that deletes a speaker given their id
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		// ADD LOGIC to check for children method before deleting
		speakerRepository.deleteById(id);
	}

	// method that takes a speaker id and update certain attributes/properties of
	// the speaker
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		Speaker existingSpeaker = speakerRepository.getOne(id);
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
		return speakerRepository.saveAndFlush(existingSpeaker);
	}

}
