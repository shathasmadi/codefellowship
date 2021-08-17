package com.example.codefellowship.model;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String body;
    private String time;
    @ManyToOne
   public ApplicationUser user;

    public Post(){

    }

    public Post(String body, String time, ApplicationUser user) {
        this.body = body;
        this.time = time;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time= time;
    }

}
