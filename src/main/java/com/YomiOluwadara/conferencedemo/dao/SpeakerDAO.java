/**
 * @author OO046152 : Yomi Oluwadara
 */
package com.YomiOluwadara.conferencedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.Speaker;

@Repository
public interface SpeakerDAO extends JpaRepository<Speaker, Long> {

}
