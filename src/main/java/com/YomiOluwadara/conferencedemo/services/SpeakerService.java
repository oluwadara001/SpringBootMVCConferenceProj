/**
 * 
 */
package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.YomiOluwadara.conferencedemo.models.Speaker;
import com.YomiOluwadara.conferencedemo.repositories.SpeakerDAO;

/**
 * @author OO046152
 *
 */

@Service
public class SpeakerService {

	@Autowired
	private SpeakerDAO speakerDAO;

//method that returns the list of all speakers
	public List<Speaker> list() {
		return speakerDAO.findAll();
	}

//method that returns a specific speaker given their id
	public Speaker get(@PathVariable Long id) {
		return speakerDAO.getOne(id);
	}

//creates a new speaker
	public Speaker create(@RequestBody Speaker speaker) {
		return speakerDAO.saveAndFlush(speaker);
	}

//method that deletes a speaker given their id
	public void delete(@PathVariable Long id) {
		// ADD LOGIC to check for children method before deleting
		speakerDAO.deleteById(id);
	}

//method that updates/patch certain attributes of a speaker 
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		Speaker existingSpeaker = speakerDAO.getOne(id);
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
		return speakerDAO.saveAndFlush(existingSpeaker);
	}

}
