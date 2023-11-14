package com.go;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go.controller.UserController;
import com.go.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@AutoConfigureMockMvc
@WebMvcTest (UserController.class)
class UserControllerTest {
    private static final String END_POINT_PATH = "api/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired 
    private ObjectMapper objectMapper;

    @MockBean 
    UserService userService;
//tests for create user

    @Test
    void testCreateUser() throws Exception {
        String userJson =     "{\"userName\":\"Paco\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create")
                .contentType("application/json")
                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("ok"))
                .andDo(print());
    }
    @Test 
    void createUserWithInvalidName() throws Exception {
        String userJson="{\"userName\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create")
                .contentType("application/json")
                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid data received"))
                .andDo(print());
    }






    //Tests for update User



    //Tests for Delete User

}