package com.YomiOluwadara.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
} 