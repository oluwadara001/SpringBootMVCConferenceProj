/**
 * @author OO046152 : Yomi Oluwadara
 */
package com.YomiOluwadara.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.models.Speaker;


public interface SpeakerDAO extends JpaRepository<Speaker, Long> {

}
