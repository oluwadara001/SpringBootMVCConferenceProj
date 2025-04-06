package com.YomiOluwadara.conferencedemo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.YomiOluwadara.conferencedemo.exceptions.ResourceNotFoundException;
import com.YomiOluwadara.conferencedemo.models.Attendee;
import com.YomiOluwadara.conferencedemo.repositories.AttendeeRepository;
import com.YomiOluwadara.conferencedemo.services.AttendeeService;

@Service
@Transactional
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    @Override
    public Attendee findById(Long id) {
        return attendeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendee not found with id: " + id));
    }

    @Override
    public Attendee save(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    @Override
    public void deleteById(Long id) {
        if (!attendeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Attendee not found with id: " + id);
        }
        attendeeRepository.deleteById(id);
    }
} 