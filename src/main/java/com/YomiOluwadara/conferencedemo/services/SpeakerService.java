package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import com.YomiOluwadara.conferencedemo.models.Speaker;

public interface SpeakerService {
    List<Speaker> findAll();
    Speaker findById(Long id);
    Speaker save(Speaker speaker);
    void deleteById(Long id);
} 