package com.YomiOluwadara.conferencedemo.services;

import java.util.Optional;

import com.YomiOluwadara.conferencedemo.models.SessionDetails;

public interface SessionDetailsService {
    SessionDetails save(SessionDetails sessionDetails);
    Optional<SessionDetails> findBySessionId(Long sessionId);
    void deleteBySessionId(Long sessionId);
    SessionDetails update(SessionDetails sessionDetails);
} 