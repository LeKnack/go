package com.go.entity;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @Id
    @JsonProperty("id")
    private String id;

    private String userName;

    //getters and setters
    public String getUserName(){
        return userName;
    }

    public void setUserName(String name){
        this.userName = name;
    }


    public User(){

    }
    public User(String userName){
        this.userName = userName;
    }
    @Override
    public String toString(){
        return String.format("User[id=%s, userName='%s']", id, userName);
    }

    public String getId() {
        return id;
    }

    

}
