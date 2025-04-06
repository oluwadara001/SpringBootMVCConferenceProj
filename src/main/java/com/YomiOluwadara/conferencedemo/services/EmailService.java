package com.YomiOluwadara.conferencedemo.services;

public interface EmailService {
    /**
     * Send a confirmation email for session registration
     * @param to The recipient's email address
     * @param subject The email subject
     * @param content The email content
     */
    void sendEmail(String to, String subject, String content);
    
    /**
     * Send a registration confirmation email
     * @param to The recipient's email address
     * @param sessionName The name of the session
     * @param sessionDate The date of the session
     * @param sessionTime The time of the session
     */
    void sendRegistrationConfirmation(String to, String sessionName, String sessionDate, String sessionTime);
} 