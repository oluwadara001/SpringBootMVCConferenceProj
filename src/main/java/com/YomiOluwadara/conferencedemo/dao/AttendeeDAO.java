/**
 * @author OO046152
 *
 */
package com.YomiOluwadara.conferencedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.model.Attendee;

public interface AttendeeDAO extends JpaRepository<Attendee, Long> {

}
