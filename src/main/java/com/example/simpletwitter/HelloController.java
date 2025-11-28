package com.example.simpletwitter;
import jakarta.servlet.http.HttpSession;
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
    @PostMapping("tweet")
    public String tweet(@RequestBody TweetRequest req,HttpSession session){
        Long userId =(Long) session.getAttribute("userId");
        User u = userRepository.findById(userId).orElse(null);
        if(u == null){
            return "用户不存在";
        }
        Tweet t = new Tweet(req.content);
        t.setUser(u);
        tweetRepository.save(t);
        return "发布成功：" + req.content;
    }
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session){
        User u = userRepository.findByUsername(user.getUsername());
        if (u != null && u.getPassword().equals(user.getPassword())){
            session.setAttribute("userId", u.getId());
            return "OK";
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
    public List<String> timeline(HttpSession session){
        Long userId =(Long) session.getAttribute("userId");
        User u = userRepository.findById(userId).orElse(null);
        List<Tweet> list = tweetRepository.findAll();
        List<String> result = new ArrayList<>();
        for(Tweet t : list){
            if(t.getUser().getId().equals(userId)) {
                result.add(t.getContent());
            }
        }
        return result;
    }
    @GetMapping("timelineall")
    public List<String> timelineALL(){
        List<Tweet> list = tweetRepository.findAll();
        List<String> result = new ArrayList<>();
        for(Tweet t : list){
            result.add(t.getContent());
        }
        return result;
    }
    static class TweetRequest{
        public String content;
    }
}
