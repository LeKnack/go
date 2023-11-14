package com.go.service;

import com.go.dto.UserDto;
import com.go.entity.User;
import com.go.entity.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;

    public Boolean validName(String name){
        return ((name.length() <= 32) && (name.length() >= 0));
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public String createUser(UserDto userDto) {
        try {
            User user = UserMappingService.mapDtoToEntity(userDto);
            System.out.println("Received request body: " + userDto);
            if (!(validName(user.getUserName())) ) {
                return "could not create user, username invalid!";
            }
            userRepository.save(user);
            return "ok";
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }

    //find user by id

    public UserDto getUserbyId(String userId) {
        try{
                Optional<User> optionalUser = userRepository.findById(userId);
                return optionalUser.map(UserMappingService::mapEntityToDto).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving users: " + e.getMessage());
        } 
    }

    public List<UserDto> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(UserMappingService::mapEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving users: " + e.getMessage());
        }
    }

    public String updateUser(String id, UserDto updatedUserDto) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            User updatedUser = UserMappingService.mapDtoToEntity(updatedUserDto);
            existingUser.setUserName(updatedUser.getUserName());
            userRepository.save(existingUser);

            return "User updated successfully!";
        } catch (Exception e) {
            return "Error updating user: " + e.getMessage();
        }
    }

    public String deleteUser(String id) {
        try {
            userRepository.deleteById(id);
            return "User deleted successfully!";
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }
}
