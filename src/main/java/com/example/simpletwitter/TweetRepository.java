package com.example.simpletwitter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository <Tweet,Long>{
}
