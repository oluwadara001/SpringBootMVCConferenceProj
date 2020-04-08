/**
 * 
 */
package com.YomiOluwadara.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.models.Attendee;

/**
 * @author OO046152
 *
 */
public interface AttendeeDAO extends JpaRepository<Attendee, Long> {

}
