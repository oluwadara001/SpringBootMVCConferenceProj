/**
 * @author OO046152
 *
 */
package com.YomiOluwadara.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.models.Attendee;

public interface AttendeeDAO extends JpaRepository<Attendee, Long> {

}
