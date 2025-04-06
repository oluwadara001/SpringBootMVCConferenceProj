/**
 * @author OO046152
 *
 */
package com.YomiOluwadara.conferencedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.Attendee;

@Repository
public interface AttendeeDAO extends JpaRepository<Attendee, Long> {

}
