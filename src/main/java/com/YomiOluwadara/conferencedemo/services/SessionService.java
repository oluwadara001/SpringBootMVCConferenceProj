package com.YomiOluwadara.conferencedemo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.YomiOluwadara.conferencedemo.models.Session;

public interface SessionService {
    /**
     * Find all sessions, sorted by start time
     */
    List<Session> findAll();
    
    /**
     * Find all sessions with eager loading of speaker information
     */
    List<Session> findAllWithSpeakers();
    
    /**
     * Find all sessions with speakers and apply custom sorting
     */
    List<Session> findAllWithSpeakers(Sort sort);
    
    /**
     * Find session by ID
     */
    Session findById(Long id);
    
    /**
     * Save or update a session
     */
    Session save(Session session);
    
    /**
     * Delete a session by ID
     */
    void deleteById(Long id);
    
    /**
     * Find sessions scheduled for a specific day
     */
    List<Session> findByDate(LocalDateTime date);
    
    /**
     * Find sessions by speaker ID
     */
    List<Session> findBySpeakerId(Long speakerId);
    
    /**
     * Find upcoming sessions
     */
    List<Session> findUpcomingSessions();
    
    /**
     * Search sessions by keyword in title or description
     */
    List<Session> searchByKeyword(String keyword);

    /**
     * Register a user for a session
     * @param sessionId The ID of the session
     * @param userId The ID of the user to register
     * @throws RuntimeException if the session is full or the user is already registered
     */
    void registerUser(Long sessionId, Long userId);

    /**
     * Unregister a user from a session
     * @param sessionId The ID of the session
     * @param userId The ID of the user to unregister
     * @throws RuntimeException if the registration is not found
     */
    void unregisterUser(Long sessionId, Long userId);
} 