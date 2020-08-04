
/**
 * @author OO046152 :: Yomi Oluwadara
 */
package com.YomiOluwadara.conferencedemo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "attendees")
public class Attendee {

	//TODO: change column names to use the camel case pattern, use the @column annotation

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attendee_id;
	private String first_name;
	private String last_name;
	private String title;
	private String company;
	private String email;
	private String phone_number;

	@OneToMany(mappedBy = "attendees")
	@JsonIgnore

	private List<Session> sessions; // pointing back to session class

	// default constructor
	public Attendee() {

	}

	public long getAttendee_id() {
		return attendee_id;
	}

	public void setAttendee_id(long attendee_id) {
		this.attendee_id = attendee_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

}
