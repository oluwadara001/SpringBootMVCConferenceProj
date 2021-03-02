/**
 * @author OO046152 : Yomi Oluwadara
 */
package com.YomiOluwadara.conferencedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.model.Speaker;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerDAO extends JpaRepository<Speaker, Long> {

}
