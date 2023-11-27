package com.go.controller;


import com.go.dto.MoveDto;
import com.go.dto.UserDto;
import com.go.service.BoardMappingService;
import com.go.service.BoardService;
import com.go.service.UserMappingService;
import com.go.view.BoardView;
import com.go.view.UserView;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    public ResponseEntity<BoardView> createBoard(@RequestBody UserDto userDto) {
        BoardView createdBoard = BoardMappingService.mapDtoToView(boardService.createBoard(userDto));
        return ResponseEntity.ok(createdBoard);
    }

    //this is a debug method and we give out ids here it has to be dsibaled later
    @GetMapping("/all")
    public ResponseEntity<List<BoardView>> getAllBoards() {
        List<BoardView> boards = boardService.getAllBoards().stream().map(BoardMappingService::mapDtoToView).toList();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardView> getBoardById(@PathVariable String boardId) {
        BoardView boardView = BoardMappingService.mapDtoToView(boardService.getBoardbyId(boardId));
        return ResponseEntity.ok(boardView);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<String> makeMove(@PathVariable String boardId, @RequestParam String userId,@RequestBody MoveDto moveDto) {
        return ResponseEntity.ok(boardService.makeMove(boardId, userId, moveDto));
    }
    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable String boardId, @RequestParam String userId) {
        String result = boardService.deleteBoard(boardId, userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/join/{boardId}")
    public ResponseEntity<String> joinBoard(@PathVariable String boardId, @RequestParam String userId) {
        String result = boardService.joinBoard(boardId, userId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/swapColors/{boardId}")
    public ResponseEntity<String> swapColors(@PathVariable String boardId, @RequestParam String userId) {
        return ResponseEntity.ok(boardService.swapColors(boardId, userId));
    }

    @PatchMapping("/leaveOrKick/{boardId}")
    public ResponseEntity<String> leaveOrKick(@PathVariable String boardId, @RequestParam String userId) {
        return ResponseEntity.ok(boardService.leaveOrKick(boardId, userId));
    }


}
