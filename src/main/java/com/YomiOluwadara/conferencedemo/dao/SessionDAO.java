/**
 * @author OO046152 :Yomi Oluwadara 
 * This interface extends JpaRepository using "Session" entity as data type, 
 * and "Long" as key to leverage the CRUD methods in this repository
 */

package com.YomiOluwadara.conferencedemo.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.Session;

@Repository
public interface SessionDAO extends JpaRepository<Session, Long> {
    
    /**
     * Custom query to find all sessions with eager loading of speaker information
     * This helps prevent N+1 query problems
     */
    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.speaker")
    List<Session> findAllWithSpeakers();
    
    /**
     * Custom query to find all sessions with eager loading of speaker information
     * and sorted by the given sort parameters
     */
    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.speaker")
    List<Session> findAllWithSpeakers(Sort sort);
    
    /**
     * Find sessions scheduled for a specific day
     */
    @Query("SELECT s FROM Session s WHERE DATE(s.startTime) = DATE(:date)")
    List<Session> findByDate(@Param("date") LocalDateTime date);
    
    /**
     * Find sessions by speaker id
     */
    List<Session> findBySpeakerId(Long speakerId);
    
    /**
     * Find upcoming sessions (sessions that haven't started yet)
     */
    List<Session> findByStartTimeAfter(LocalDateTime dateTime);
    
    /**
     * Search sessions by keyword in title or description
     */
    @Query("SELECT s FROM Session s WHERE LOWER(s.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Session> searchByKeyword(@Param("keyword") String keyword);
}
