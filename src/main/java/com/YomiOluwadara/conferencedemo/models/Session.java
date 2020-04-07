/**
 * 
 */
package com.YomiOluwadara.conferencedemo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author OO046152:Yomi Oluwadara
 * 
 *         Session class maps to the conference sessions annotate this class as
 *         JPA entity with the @Entity
 * @Entity(name = "sessions") attributes this class to the database table called
 *              session
 * 
 * @JsonIgnoreProperties : helps handle loading
 */

//@Entity
//@Table(name = "sessions")
@Entity(name = "sessions")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Session {

	/*
	 * note on variable names variables- keeping them the same way they are defined
	 * in the session database schema making jpa auto-bing to them - no need for
	 * annotations , if you change the variables so they now different from how they
	 * were declared in the database schema, you must add @column annotation to each
	 * of the variable
	 * 
	 * @id is the primary key, hence the annotation, and its auto generated
	 */

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long session_id;
	private String session_name;
	private String session_description;
	private int session_length;
	@JsonIgnore // helps with serialization issues : ignores nested json pay loads
	@ManyToMany
	@JoinTable(name = "session_speakers", joinColumns = @JoinColumn(name = "session_id"), inverseJoinColumns = @JoinColumn(name = "speaker_id"))
	private List<Speaker> speakers;

	/*
	 * private List<Speaker> speakers; is the attribute pointing to the Speaker
	 * class adding variable from Speaker class so we can tie the two model classes
	 * together with JPA relationships that represent their exact database
	 * relationship
	 * 
	 * @ManyToMany- defines many to many, one speaker might have several sessions
	 * and vice versa
	 * 
	 * @JoinTable- defines the join tables and the foreign keys
	 */

	public Long getSession_id() {
		return session_id;
	}

	public void setSession_id(Long session_id) {
		this.session_id = session_id;
	}

	public String getSession_name() {
		return session_name;
	}

	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}

	public String getSession_description() {
		return session_description;
	}

	public void setSession_description(String session_description) {
		this.session_description = session_description;
	}

	public int getSession_length() {
		return session_length;
	}

	public void setSession_length(int session_length) {
		this.session_length = session_length;
	}

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

	/*
	 * default constructor- will help with serialization and de-serialization which
	 * will happen when we plug the controllers to match the data into and out of
	 * JSON
	 */
	public Session() {

	}

	
	

}
