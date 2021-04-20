/**
 * @author OO046152:Yomi Oluwadara
 * <p>
 * Session class maps to the conference sessions annotate this class as
 * JPA entity with the @Entity
 * @Entity(name = "sessions") attributes this class to the database table called
 * session
 * @JsonIgnoreProperties : helps handle loading
 * <p>
 * /* note on variable names variables- keeping them the
 * same way they are defined in the session database
 * schema making jpa auto-bing to them - no need for
 * annotations , if you change the variables so they now
 * different from how they were declared in the database
 * schema, you must add @column annotation to each of the
 * variable
 * @id is the primary key, hence the annotation, and its auto generated
 * <p>
 * /* private List<Speaker> speakers; is the attribute pointing to the
 * Speaker class adding variable from Speaker class so we can tie the two
 * model classes together with JPA relationships that represent their exact
 * database relationship
 * @ManyToMany- defines many to many, one speaker might have several sessions
 * and vice versa
 * @JoinTable- defines the join tables and the foreign keys
 * <p>
 * /* default constructor- will help with serialization and
 * de-serialization which will happen when we plug the controllers
 * to match the data into and out of JSON
 */

package com.YomiOluwadara.conferencedemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {

	//TODO: change column names to use the camel case pattern, use the @column annotation

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "session_id")
	private long sessionId;

	@Column(name = "session_name")
	private String sessionName;

	@Column(name = "session_description")
	private String sessionDescription;

	@Column(name = "session_length")
	private int sessionLength;

	@JsonIgnore // helps with serialization issues : ignores nested json pay loads
	@ManyToMany
	@JoinTable(name = "session_speakers", joinColumns = @JoinColumn(name = "session_id"),
			inverseJoinColumns = @JoinColumn(name = "speaker_id"))
	private List<Speaker> speakers;

	@JsonIgnore
	@OneToMany
	private List<Attendee> attendees;

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getSessionDescription() {
		return sessionDescription;
	}

	public void setSessionDescription(String sessionDescription) {
		this.sessionDescription = sessionDescription;
	}

	public int getSessionLength() {
		return sessionLength;
	}

	public void setSessionLength(int sessionLength) {
		this.sessionLength = sessionLength;
	}

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

	public List<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}

	public Session() {

	}

	public Session(long sessionId, String sessionName, String sessionDescription, int sessionLength,
				   List<Speaker> speakers, List<Attendee> attendees) {
		super();
		this.sessionId = sessionId;
		this.sessionName = sessionName;
		this.sessionDescription = sessionDescription;
		this.sessionLength = sessionLength;
		this.speakers = speakers;
		this.attendees = attendees;
	}

}
