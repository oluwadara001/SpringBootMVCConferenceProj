package com.YomiOluwadara.conferencedemo.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.YomiOluwadara.conferencedemo.dao.RegistrationDAO;
import com.YomiOluwadara.conferencedemo.dao.SessionDAO;
import com.YomiOluwadara.conferencedemo.dao.UserDao;
import com.YomiOluwadara.conferencedemo.models.Registration;
import com.YomiOluwadara.conferencedemo.models.Session;
import com.YomiOluwadara.conferencedemo.models.User;
import com.YomiOluwadara.conferencedemo.services.EmailService;
import com.YomiOluwadara.conferencedemo.services.RegistrationService;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationDAO registrationDAO;
    private final SessionDAO sessionDAO;
    private final UserDao userDao;
    private final EmailService emailService;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a");

    public RegistrationServiceImpl(RegistrationDAO registrationDAO, SessionDAO sessionDAO, UserDao userDao, EmailService emailService) {
        this.registrationDAO = registrationDAO;
        this.sessionDAO = sessionDAO;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @Override
    public Registration register(Long sessionId, Long userId) {
        Session session = sessionDAO.findById(sessionId)
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
        
        registration = registrationDAO.save(registration);
        
        // Send confirmation email
        String sessionDate = session.getStartTime().format(DATE_FORMATTER);
        String sessionTime = session.getStartTime().format(TIME_FORMATTER);
        emailService.sendRegistrationConfirmation(
            user.getEmail(),
            session.getTitle(),
            sessionDate,
            sessionTime
        );
        
        return registration;
    }

    @Override
    public void cancelRegistration(Long registrationId) {
        Registration registration = registrationDAO.findById(registrationId)
            .orElseThrow(() -> new RuntimeException("Registration not found with id: " + registrationId));
        
        registration.setStatus("cancelled");
        registrationDAO.save(registration);
    }

    @Override
    public List<Registration> findBySessionId(Long sessionId) {
        return registrationDAO.findBySessionId(sessionId);
    }

    @Override
    public List<Registration> findByUserId(Long userId) {
        return registrationDAO.findByUserId(userId);
    }

    @Override
    public Long getConfirmedRegistrationsCount(Long sessionId) {
        return registrationDAO.countConfirmedRegistrations(sessionId);
    }

    @Override
    public boolean isUserRegistered(Long sessionId, Long userId) {
        return registrationDAO.findBySessionIdAndUserId(sessionId, userId) != null;
    }

    @Override
    public Registration findById(Long id) {
        return registrationDAO.findById(id)
            .orElseThrow(() -> new RuntimeException("Registration not found with id: " + id));
    }

    @Override
    public Registration findBySessionIdAndUserId(Long sessionId, Long userId) {
        return registrationDAO.findBySessionIdAndUserId(sessionId, userId);
    }
} 