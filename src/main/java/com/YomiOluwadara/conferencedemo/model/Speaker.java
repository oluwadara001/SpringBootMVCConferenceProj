/**
 * @author OO046152:Yomi Oluwadara
 * Tables in the model package depicts the tables in the DB. Each class is
 * mapped to a database tables: class variable are mapped to database columns
 * @JsonIgnore : helps with serialization
 */

package com.YomiOluwadara.conferencedemo.model;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "speakers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "speaker_id")
	private Long speakerId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String title;
	private String company;
	@Column(name = "speaker_bio")
	private String speakerBio;
	// using byte type for photo @lob = large object
	// @Type help hibernate deal with binary data that could be large
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "speaker_photo")
	private byte[] speakerPhoto;

	@ManyToMany(mappedBy = "speakers")
	@JsonIgnore
	private List<Session> sessions; // pointing back to session class

	public String getSpeakerBio() {
		return speakerBio;
	}

	public void setSpeakerBio(String speakerBio) {
		this.speakerBio = speakerBio;
	}

	public Long getSpeaker_id() {
		return speakerId;
	}

	public void setSpeaker_id(long speaker_id) {
		this.speakerId = speaker_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public byte[] getSpeakerPhoto() {
		return speakerPhoto;
	}

	public void setSpeakerPhoto(byte[] speakerPhoto) {
		this.speakerPhoto = speakerPhoto;
	}

	public void setSpeaker_id(Long speaker_id) {
		this.speakerId = speaker_id;
	}

	public Speaker() {

	}

	public Speaker(Long speaker_id, String firstName, String lastName, String title, String company,
				   String speakerBio, byte[] speakerPhoto, List<Session> sessions) {
		super();
		this.speakerId = speaker_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.company = company;
		this.speakerBio = speakerBio;
		this.speakerPhoto = speakerPhoto;
		this.sessions = sessions;
	}

}
