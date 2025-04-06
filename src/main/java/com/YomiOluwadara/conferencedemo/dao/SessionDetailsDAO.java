package com.YomiOluwadara.conferencedemo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.models.SessionDetails;

public interface SessionDetailsDAO extends JpaRepository<SessionDetails, Long> {
    Optional<SessionDetails> findBySession(Session session);
    Optional<SessionDetails> findBySessionId(Long sessionId);
} 