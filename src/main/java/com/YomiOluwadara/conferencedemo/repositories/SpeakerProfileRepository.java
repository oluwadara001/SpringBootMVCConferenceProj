package com.YomiOluwadara.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.SpeakerProfile;

@Repository
public interface SpeakerProfileRepository extends JpaRepository<SpeakerProfile, Long> {
    SpeakerProfile findBySpeakerId(Long speakerId);
} 