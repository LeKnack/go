package com.go.dto;




public class UserDto {


    
    private String id;

    private String userName;
    //String up to 32 characters long
    

    public void setId(String id) {
        this.id = id;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getId() {
        return id;
    }


    public String getUserName() {
        return userName;
    }
    
}
