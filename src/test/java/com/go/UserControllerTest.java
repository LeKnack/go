package com.go;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.go.controller.UserController;
import com.go.service.UserService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.apache.tomcat.util.http.parser.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    private static final String END_POINT_PATH = "api/users";

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    UserService userService;

    private static String testUserId;
//tests for create user

    @Test
    @Order(1)
    void testCreateUser() throws Exception {
        String userJson =     "{\"userName\":\"Paco\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create")
                .contentType("application/json")
                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        String sucessPrefix = "succesfully created user";
        testUserId = responseContent.substring(sucessPrefix.length()).trim();
        // Check if the response content starts with the expected prefix
        assertTrue(responseContent.startsWith(sucessPrefix));

    }
    @Test
    @Order(2) 
    void createUserWithInvalidName() throws Exception {
        String userJson="{\"userName\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create")
                .contentType("application/json")
                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid data received"))
                .andDo(print());
    }

    //Test for getUserbyId
    @Test
    @Order(3)
    void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + testUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testUserId))
                .andDo(print());
    }
    //Test for update
    @Test
    @Order(4)
    void testUpdateUser() throws Exception {
        // Use the saved user ID from the previous test
        String updatedUserJson = "{\"id\":\"" + testUserId + "\",\"userName\":\"UpdatedName\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/" + testUserId)
                .contentType("application/json")
                .content(updatedUserJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User updated successfully!"))
                .andDo(print());
    }
    @Test
    @Order(5)
    void testDeleteUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/" + testUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User deleted successfully!"))
                .andDo(print());
    }

}