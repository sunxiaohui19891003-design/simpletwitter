package com.example.simpletwitter;

//import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;
    //List<String> tweets;
    //public HelloController(){
    //    tweets = new ArrayList<>();
    //}
    @PostMapping("tweet")
    public String tweet(@RequestBody String test){
        tweetRepository.save(new Tweet(test));
        return "你的推文是：" + test;
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        User u = userRepository.findByUsername(user.username);
        if (u != null && u.password.equals(user.password)){
            return"登录成功";
        } else {
            return "用户名或密码错误";
        }

    }
    @PostMapping("/register")
    public String register(@RequestBody User user){
        userRepository.save(user);
        return"注册成功";
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
