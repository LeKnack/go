package com.go.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

public class User {
    @Id
    private String id;
    
    private String userName;

    //getters and setters
    public String getUserName(){
        return userName;
    }

    public void setUserName(String name){
        this.userName = name;
    }


    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String userName) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
    }
    @Override
    public String toString(){
        return String.format("User[id=%s, userName='%s']", id, userName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    

}
