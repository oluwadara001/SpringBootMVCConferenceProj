package com.YomiOluwadara.conferencedemo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.YomiOluwadara.conferencedemo.models.Speaker;
import com.YomiOluwadara.conferencedemo.repositories.SpeakerRepository;
import com.YomiOluwadara.conferencedemo.services.SpeakerService;

@Service
@Transactional
public class SpeakerServiceImpl implements SpeakerService {
    private final SpeakerRepository speakerRepository;

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker findById(Long id) {
        return speakerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Speaker not found with id: " + id));
    }

    @Override
    public Speaker save(Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    @Override
    public void deleteById(Long id) {
        speakerRepository.deleteById(id);
    }
} 