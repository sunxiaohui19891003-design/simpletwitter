package com.example.simpletwitter;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.tools.JavaFileManager;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
