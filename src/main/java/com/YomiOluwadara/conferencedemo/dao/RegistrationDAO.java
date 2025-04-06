package com.YomiOluwadara.conferencedemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.Registration;

@Repository
public interface RegistrationDAO extends JpaRepository<Registration, Long> {
    
    @Query("SELECT r FROM Registration r WHERE r.session.id = :sessionId")
    List<Registration> findBySessionId(@Param("sessionId") Long sessionId);
    
    @Query("SELECT r FROM Registration r WHERE r.user.id = :userId")
    List<Registration> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(r) FROM Registration r WHERE r.session.id = :sessionId AND r.status = 'confirmed'")
    Long countConfirmedRegistrations(@Param("sessionId") Long sessionId);
    
    @Query("SELECT r FROM Registration r WHERE r.session.id = :sessionId AND r.user.id = :userId")
    Registration findBySessionIdAndUserId(@Param("sessionId") Long sessionId, @Param("userId") Long userId);
} 