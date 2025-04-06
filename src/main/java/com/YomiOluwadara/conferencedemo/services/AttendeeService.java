package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import com.YomiOluwadara.conferencedemo.models.Attendee;

public interface AttendeeService {
    List<Attendee> findAll();
    Attendee findById(Long id);
    Attendee save(Attendee attendee);
    void deleteById(Long id);
} 