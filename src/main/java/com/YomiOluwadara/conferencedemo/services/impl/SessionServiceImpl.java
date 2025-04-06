package com.YomiOluwadara.conferencedemo.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.YomiOluwadara.conferencedemo.dao.RegistrationDAO;
import com.YomiOluwadara.conferencedemo.dao.SessionDAO;
import com.YomiOluwadara.conferencedemo.dao.UserDao;
import com.YomiOluwadara.conferencedemo.models.Registration;
import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.models.User;
import com.YomiOluwadara.conferencedemo.services.SessionService;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private final SessionDAO sessionRepository;
    private final RegistrationDAO registrationDAO;
    private final UserDao userDao;

    public SessionServiceImpl(SessionDAO sessionRepository, 
                            RegistrationDAO registrationDAO,
                            UserDao userDao) {
        this.sessionRepository = sessionRepository;
        this.registrationDAO = registrationDAO;
        this.userDao = userDao;
    }

    @Override
    public List<Session> findAll() {
        // Sort sessions by start time to show them in chronological order
        return sessionRepository.findAll(Sort.by(Sort.Direction.ASC, "startTime"));
    }
    
    @Override
    public List<Session> findAllWithSpeakers() {
        return sessionRepository.findAllWithSpeakers();
    }
    
    @Override
    public List<Session> findAllWithSpeakers(Sort sort) {
        return sessionRepository.findAllWithSpeakers(sort);
    }

    @Override
    public Session findById(Long id) {
        Optional<Session> sessionOptional = sessionRepository.findById(id);
        if (sessionOptional.isPresent()) {
            return sessionOptional.get();
        } else {
            throw new RuntimeException("Session not found with id: " + id);
        }
    }

    @Override
    public Session save(Session session) {
        // Set timestamps if they're not already set
        if (session.getCreatedAt() == null) {
            session.prePersist();
        } else {
            session.preUpdate();
        }
        return sessionRepository.save(session);
    }

    @Override
    public void deleteById(Long id) {
        // Check if session exists before attempting to delete
        if (sessionRepository.existsById(id)) {
            sessionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete - Session not found with id: " + id);
        }
    }
    
    @Override
    public List<Session> findByDate(LocalDateTime date) {
        return sessionRepository.findByDate(date);
    }
    
    @Override
    public List<Session> findBySpeakerId(Long speakerId) {
        return sessionRepository.findBySpeakerId(speakerId);
    }
    
    @Override
    public List<Session> findUpcomingSessions() {
        return sessionRepository.findByStartTimeAfter(LocalDateTime.now());
    }
    
    @Override
    public List<Session> searchByKeyword(String keyword) {
        return sessionRepository.searchByKeyword(keyword);
    }

    @Override
    public void registerUser(Long sessionId, Long userId) {
        Session session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("Session not found with id: " + sessionId));
        
        User user = userDao.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        // Check if user is already registered
        Registration existingRegistration = registrationDAO.findBySessionIdAndUserId(sessionId, userId);
        if (existingRegistration != null) {
            throw new RuntimeException("User is already registered for this session");
        }
        
        // Check if session is full
        Long confirmedRegistrations = registrationDAO.countConfirmedRegistrations(sessionId);
        if (confirmedRegistrations >= session.getCapacity()) {
            throw new RuntimeException("Session is already full");
        }
        
        Registration registration = new Registration();
        registration.setSession(session);
        registration.setUser(user);
        registration.setRegistrationDate(LocalDateTime.now());
        registration.setStatus("confirmed");
        
        registrationDAO.save(registration);
    }

    @Override
    public void unregisterUser(Long sessionId, Long userId) {
        Registration registration = registrationDAO.findBySessionIdAndUserId(sessionId, userId);
        if (registration == null) {
            throw new RuntimeException("Registration not found for session " + sessionId + " and user " + userId);
        }
        
        registration.setStatus("cancelled");
        registrationDAO.save(registration);
    }
} 