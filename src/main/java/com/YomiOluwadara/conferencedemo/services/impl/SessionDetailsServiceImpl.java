package com.YomiOluwadara.conferencedemo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YomiOluwadara.conferencedemo.dao.SessionDetailsDAO;
import com.YomiOluwadara.conferencedemo.models.SessionDetails;
import com.YomiOluwadara.conferencedemo.services.SessionDetailsService;

@Service
public class SessionDetailsServiceImpl implements SessionDetailsService {

    @Autowired
    private SessionDetailsDAO sessionDetailsDAO;

    @Override
    public SessionDetails save(SessionDetails sessionDetails) {
        return sessionDetailsDAO.save(sessionDetails);
    }

    @Override
    public Optional<SessionDetails> findBySessionId(Long sessionId) {
        return sessionDetailsDAO.findBySessionId(sessionId);
    }

    @Override
    public void deleteBySessionId(Long sessionId) {
        sessionDetailsDAO.findBySessionId(sessionId)
            .ifPresent(sessionDetailsDAO::delete);
    }

    @Override
    public SessionDetails update(SessionDetails sessionDetails) {
        if (sessionDetails.getId() == null) {
            throw new IllegalArgumentException("Cannot update session details without an ID");
        }
        return sessionDetailsDAO.save(sessionDetails);
    }
} 