package com.YomiOluwadara.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
} 