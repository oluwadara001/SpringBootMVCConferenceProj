/**
 * @author OO046152
 *
 */
package com.YomiOluwadara.conferencedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.model.Attendee;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeDAO extends JpaRepository<Attendee, Long> {

}
