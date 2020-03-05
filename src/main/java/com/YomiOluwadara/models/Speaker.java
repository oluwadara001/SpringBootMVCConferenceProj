/**
 * 
 */
package com.YomiOluwadara.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author OO046152
 *
 */

@Entity(name = "speakers")
public class Speaker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long speaker_id;
	private String first_name;
	private String last_name;
	private String title;
	private String company;
	private String speaker_bio;
	@ManyToMany(mappedBy = "speakers")
	private List<Session> sessions; // pointing back to session class

	public String getSpeaker_bio() {
		return speaker_bio;
	}

	public void setSpeaker_bio(String speaker_bio) {
		this.speaker_bio = speaker_bio;
	}

	public long getSpeaker_id() {
		return speaker_id;
	}

	public void setSpeaker_id(long speaker_id) {
		this.speaker_id = speaker_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	// default class constructor
	public Speaker() {

	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

}
