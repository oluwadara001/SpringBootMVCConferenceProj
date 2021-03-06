/**
 * @author OO046152 :Yomi Oluwadara interfaces will extend the JpaReposity using
 * "Session" class/entity as data type, and, "Long" as key so it can
 * leverage the CRUD methods in this repository when writing services
 * <p>
 * SessionRpository could be traditionally be called SessionDao
 */

package com.YomiOluwadara.conferencedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.YomiOluwadara.conferencedemo.model.Session;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDAO extends JpaRepository<Session, Long> {

}
