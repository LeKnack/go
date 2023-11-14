package com.go.controller;


import com.go.dto.MoveDto;
import com.go.dto.UserDto;
import com.go.service.BoardMappingService;
import com.go.service.BoardService;
import com.go.view.BoardView;
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

    @PutMapping("/{boardId}")
    public ResponseEntity<String> makeMove(@PathVariable String boardId,@RequestBody MoveDto moveDto) {
        return ResponseEntity.ok(boardService.makeMove(boardId, moveDto));
    }
    // Add other endpoints for updating, deleting, and retrieving individual boards as needed
}
