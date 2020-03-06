/**
 * 
 */
package com.YomiOluwadara.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.YomiOluwadara.models.Speaker;
import com.YomiOluwadara.repositories.SpeakerRepository;

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

}
