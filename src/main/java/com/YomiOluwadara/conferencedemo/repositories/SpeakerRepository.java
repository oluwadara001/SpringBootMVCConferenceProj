package com.YomiOluwadara.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.Speaker;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
} 