package com.go.controller;

import com.go.dto.UserDto;
import com.go.service.UserMappingService;
import com.go.service.UserService;
import com.go.view.UserView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserView userView) {
        String serviceResponse = userService.createUser(UserMappingService.mapViewToDto(userView));
        //check for error creating user
        if (!(serviceResponse == "ok")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data received");
        }
        return ResponseEntity.ok(serviceResponse);
    }

    //this is a debug method an it should be dsiabled later we hand out id's here thats why we use Dtos
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserView> getUserById(@PathVariable String userId) {
        UserView user = UserMappingService.mapDtoToView(userService.getUserbyId(userId));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserView updatedUserView) {
        return ResponseEntity.ok(userService.updateUser(id, UserMappingService.mapViewToDto(updatedUserView)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
