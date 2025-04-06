package com.YomiOluwadara.conferencedemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.YomiOluwadara.conferencedemo.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public void sendRegistrationConfirmation(String to, String sessionName, String sessionDate, String sessionTime) {
        String subject = "Registration Confirmation - " + sessionName;
        String content = String.format(
            "Dear Participant,\n\n" +
            "Your registration for the session '%s' has been confirmed.\n\n" +
            "Session Details:\n" +
            "Date: %s\n" +
            "Time: %s\n\n" +
            "We look forward to seeing you at the session!\n\n" +
            "Best regards,\n" +
            "Conference Team",
            sessionName, sessionDate, sessionTime
        );
        
        sendEmail(to, subject, content);
    }
} 