/**
 * @author OO046152
 */
package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.YomiOluwadara.conferencedemo.model.Speaker;
import com.YomiOluwadara.conferencedemo.dao.SpeakerDAO;

@Service
public class SpeakerService {

	@Autowired
	private SpeakerDAO speakerDAO;

	/**
	 * @return a list of all speakers
	 */
	public List<Speaker> allSpeakers() {
		return speakerDAO.findAll();
	}

	/**
	 * @param id sepaker id
	 * @return a specific speaker given their id
	 */
	public Speaker findOneSpeaker(@PathVariable Long id) {
		return speakerDAO.getOne(id);
	}

	/**
	 * @param speaker to be added
	 * @return newly added speaker
	 */
	public Speaker addNewSpeaker(@RequestBody Speaker speaker) {
		return speakerDAO.saveAndFlush(speaker);
	}

	/**
	 * @param id speaker id to be deleted
	 */
	public void deleteOneSpeaker(@PathVariable Long id) {
		// TODO ADD LOGIC to check for children method before deleting
		speakerDAO.deleteById(id);
	}

	/**
	 * @param id to be uodated
	 * @param speaker speaker to be uodated
	 * @return updated speaker
	 */
	public Speaker updateSpeakerInfo(@PathVariable Long id, @RequestBody Speaker speaker) {
		Speaker existingSpeaker = speakerDAO.getOne(id);
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
		return speakerDAO.saveAndFlush(existingSpeaker);
	}
}
