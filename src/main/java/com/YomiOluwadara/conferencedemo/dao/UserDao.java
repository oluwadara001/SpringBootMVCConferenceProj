package com.YomiOluwadara.conferencedemo.dao;

import com.YomiOluwadara.conferencedemo.model.Speaker;
import com.YomiOluwadara.conferencedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>  {
}
