
/**
 * @author OO046152 :: Yomi Oluwadara
 */
package com.YomiOluwadara.conferencedemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "attendees")
public class Attendee {

	//TODO: change column names to use the camel case pattern, use the @column annotation

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendee_id")
	private long attendeeId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String title;
	private String company;
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany(mappedBy = "attendees")
	@JsonIgnore
	private List<Session> sessions; // pointing back to session class

	// default constructor
	public Attendee() {

	}

	public long getAttendeeId() {
		return attendeeId;
	}

	public void setAttendeeId(long attendeeId) {
		this.attendeeId = attendeeId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

}
