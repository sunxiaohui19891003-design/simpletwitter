package com.example.simpletwitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private TweetRepository tweetRepository;
    //List<String> tweets;
    //public HelloController(){
    //    tweets = new ArrayList<>();
    //}
    @PostMapping("tweet")
    public String tweet(@RequestBody String test){
        tweetRepository.save(new Tweet(test));
        return "你的推文是：" + test;
    }
    @GetMapping("timeline")
    public List<String> timeline(){
        List<Tweet> list = tweetRepository.findAll();
        List<String> result = new ArrayList<>();
        for(Tweet t : list){
            result.add(t.getContent());
        }
        return result;
    }

}
