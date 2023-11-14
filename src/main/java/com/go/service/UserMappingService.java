package com.go.service;

import com.go.dto.UserDto;
import com.go.entity.User;
import com.go.view.UserView;



public class UserMappingService {

    public static User mapDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        return user;
    }

    public static UserDto mapEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    public static UserView mapDtoToView(UserDto userDto) {
        UserView userView = new UserView();
        userView.setUserName(userDto.getUserName());
        return userView;
    }

    public static UserDto mapViewToDto(UserView userView) {
        UserDto userDto = new UserDto();
        userDto.setUserName(userView.getUserName());
        return userDto;
    }
    
}
