package com.YomiOluwadara.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YomiOluwadara.conferencedemo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
} 