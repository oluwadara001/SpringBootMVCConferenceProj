package com.YomiOluwadara.conferencedemo.dao;

import com.YomiOluwadara.conferencedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
