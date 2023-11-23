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
import com.go.controller.BoardController;
import com.go.dto.MoveDto;
import com.go.dto.UserDto;
import com.go.service.BoardService;
import com.go.service.UserMappingService;
import com.go.view.BoardView;
import com.go.view.UserView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.apache.tomcat.util.http.parser.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardControllerTest {
    private static final String END_POINT_PATH = "api/boards";

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    BoardService boardService;

    private static String testBoardId;
    private static String testUserId;
//tests for create user

    @Test
    @Order(1)
    void testCreateBoard() throws Exception {
        String userJson =     "{\"userName\":\"Paco\"}";
        //create a user to make a board
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
        
        //get the userinformation
        MvcResult resultGet = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + testUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testUserId))
                .andReturn();

        String responseContentGet = resultGet.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        UserView userView = objectMapper.readValue(responseContentGet, UserView.class);
        assertEquals(testUserId, userView.getId());
        //create a new board with the user as owner
        UserDto userDto = UserMappingService.mapViewToDto(userView);
            MvcResult resultCreateBoard = mockMvc.perform(MockMvcRequestBuilders.post("/api/boards/create")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(userDto)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

    String responseContentCreateBoard = resultCreateBoard.getResponse().getContentAsString();
    BoardView boardView = objectMapper.readValue(responseContentCreateBoard, BoardView.class);

    assertNotNull(boardView.getId());
    assertEquals(testUserId, boardView.getCreatorId());
    testBoardId = boardView.getId();
    }
    //test to make a move
    @Test
    @Order(2)
    void testMakeMove() throws Exception {
        // 1. Make a move on the existing board
        MoveDto moveDto = new MoveDto();
        moveDto.setRow(1);
        moveDto.setCol(1);
        moveDto.setColor(1);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/boards/" + testBoardId)
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(moveDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print()); // Use andDo(print()) for debugging if needed
    }
    //test to make a move
    @Test
    @Order(2)
    void testCapture() throws Exception {
        // 1. Make a move on the existing board
        MoveDto moveDto = new MoveDto();
        moveDto.setRow(1);
        moveDto.setCol(1);
        moveDto.setColor(1);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/boards/" + testBoardId)
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(moveDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print()); // Use andDo(print()) for debugging if needed
    }
    //test for move outside the board

    //test for move on occupied space

}