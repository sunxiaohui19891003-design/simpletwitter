package com.example.simpletwitter;
import jakarta.persistence.*;
@Entity
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    public Tweet(){
    }
    public Tweet(String content){
        this.content=content;
    }
    @ManyToOne
    private User user;
    public User getUser() {
        return user;
    }
    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setUser(User u) {
        this.user = u;
    }
}
