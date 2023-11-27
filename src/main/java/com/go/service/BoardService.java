package com.go.service;

import com.go.dto.BoardDto;
import com.go.dto.MoveDto;
import com.go.dto.UserDto;
import com.go.entity.Board;
import com.go.entity.BoardRepository;
import com.go.entity.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }



    public BoardDto createBoard(UserDto userDto) {
        try{
        //creates an empty board with the creator playing as white and black as default
        Board board = new Board(userDto.getId());
        boardRepository.save(board);
        return BoardMappingService.mapEntityToDto(board);
        } catch (Exception e) {
            throw new RuntimeException("Error creating board: " + e.getMessage());
        }
    }

    public List<BoardDto> getAllBoards() {
        try{
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardMappingService::mapEntityToDto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving boards: " + e.getMessage());
        }
    }

    public String makeMove(String id, String userId, MoveDto moveDto){
        //check weather the moves coordinates are valid
        if (!(moveDto.getCol() >= 0 && moveDto.getCol() <= 18 )){
            return "invalid move, invalid column";
        }
        if (!(moveDto.getRow() >= 0 && moveDto.getRow() <= 18 )){
            return "invalid move, invalid row";
        }
        if(!(moveDto.getColor()>= 0 && moveDto.getColor() <= 2)){
            return "invalid move, invaid color";
        }



        try{//load board from database
            Board board = boardRepository.findById(id)
                                    .orElseThrow(() -> new RuntimeException("board not found!"));
            //check weather the user is authorized to make the move
            if (moveDto.getColor() == 1 && !(userId.equals(board.getBlackPlayerId()))) {
                return "Illegal Move, you are not playing as black";
            } else if (moveDto.getColor() == 2 && !(userId.equals(board.getWhitePlayerId()))) {
                return "illegal Move, you are not playing as white";
            }
            //check weather the spot on the board is empty
            if(board.getBoardState()[moveDto.getRow()][moveDto.getCol()]  !=0){
                return "illegal move, the spot is occupied";
            } 


            //make the actual move
            //board.setSpot(moveDto.getRow(), moveDto.getCol(), moveDto.getColor());
            if(board.updateBoard(MoveMappingService.dtoToEntity(moveDto))){
                boardRepository.save(board);
                return "move made successfully";
            } 
            return "move not possible";

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving board: " + e.getMessage());
        } 
    }

    public BoardDto getBoardbyId(String boardId) {
        try{
                Optional<Board> optionalBoard = boardRepository.findById(boardId);
                return optionalBoard.map(BoardMappingService::mapEntityToDto).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving board: " + e.getMessage());
        } 
    }

    public String deleteBoard(String boardId, String userId) {
        try {
            // Load board from database
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new RuntimeException("Board not found!"));

            // Check if the user is the owner of the board
            if (!userId.equals(board.getCreatorId())) {
                return "You are not authorized to delete this board.";
            }
            boardRepository.deleteById(boardId);
            return "Board deleted successfully.";

        } catch (Exception e) {
            throw new RuntimeException("Error deleting board: " + e.getMessage());
        }
    }

    public String joinBoard(String boardId, String userId) {
        try {
            // Load board from database
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new RuntimeException("Board not found!"));

            // Check if the user is already part of the board
            if (userId.equals(board.getWhitePlayerId()) || userId.equals(board.getBlackPlayerId())) {
                return "You are already part of this board.";
            }
            // Set the user as whitePlayerId if not already assigned
            if ((board.getCreatorId().equals(board.getCreatorId())) && (board.getBlackPlayerId().equals(board.getCreatorId()))) {
                board.setWhitePlayerId(userId);
                boardRepository.save(board);
                return "Joined board.";
            } else {
                return "Board already full.";
            }

        } catch (Exception e) {
            throw new RuntimeException("Error joining board: " + e.getMessage());
        }
    }

    public String swapColors(String boardId, String userId) {
        try {
            // Load board from database
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new RuntimeException("Board not found!"));
    
            // Check if the user is the creator
            if (!userId.equals(board.getCreatorId())) {
                return "Only the creator can swap colors.";
            }
            // Only possible on an empty board
            if (Arrays.stream(board.getBoardState()).flatMapToInt(Arrays::stream).anyMatch(cell -> cell != 0)) {
                return "Cannot swap colors after moves have been played.";
            }
        
            // Swap the player Ids
            String temp = board.getWhitePlayerId();
            board.setWhitePlayerId(board.getBlackPlayerId());
            board.setBlackPlayerId(temp);
    
            boardRepository.save(board);
            return "Colors swapped successfully.";
        } catch (Exception e) {
            throw new RuntimeException("Error swapping colors: " + e.getMessage());
        }
    }

    public String leaveOrKick(String boardId, String userId) {
        try {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new RuntimeException("Board not found!"));

            String creatorId = board.getCreatorId();
            String whitePlayerId = board.getWhitePlayerId();
            String blackPlayerId = board.getBlackPlayerId();

            if (userId.equals(creatorId)) {
                // Kick the other player
                board.setWhitePlayerId(creatorId);
                board.setBlackPlayerId(creatorId);
                boardRepository.save(board);
                return "kicked player successfully.";

            } else if (userId.equals(whitePlayerId) || userId.equals(blackPlayerId)) {
                // Leave the game
                board.setWhitePlayerId(creatorId);
                board.setBlackPlayerId(creatorId);
                boardRepository.save(board);
                return "left board successfully.";
            }

            boardRepository.save(board);
            return "Leave or kick action performed successfully.";

        } catch (Exception e) {
            throw new RuntimeException("Error in leaveOrKick: " + e.getMessage());
        }
    }



}
