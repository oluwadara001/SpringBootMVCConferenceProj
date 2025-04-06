package com.YomiOluwadara.conferencedemo.services;

import java.util.List;

import com.YomiOluwadara.conferencedemo.models.Registration;

public interface RegistrationService {
    Registration register(Long sessionId, Long userId);
    void cancelRegistration(Long registrationId);
    List<Registration> findBySessionId(Long sessionId);
    List<Registration> findByUserId(Long userId);
    Long getConfirmedRegistrationsCount(Long sessionId);
    boolean isUserRegistered(Long sessionId, Long userId);
    Registration findById(Long id);
    Registration findBySessionIdAndUserId(Long sessionId, Long userId);
} 