package com.go.service;

import com.go.dto.BoardDto;
import com.go.dto.MoveDto;
import com.go.dto.UserDto;
import com.go.entity.Board;
import com.go.entity.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String makeMove(String id, MoveDto moveDto){
        

        //check weather the move is valid
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
            //check weather the spot on the board is empty
            if(board.getBoardState()[moveDto.getCol()][moveDto.getRow()]  !=0){
                return "illegal move, the spot is occupied";
            } 
            //Capture and KO-logic needs to go here

            //make the actual move
            board.setSpot(moveDto.getRow(), moveDto.getCol(), moveDto.getColor());
            boardRepository.save(board);
            return "move made successfully";

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving board: " + e.getMessage());
        } 
    }

    public String takeBack(String boardId){
        return "so you want to take back...";
    }

    public String joinBoard(String boardId, String userId){
        return "Attempting to join board";
    }

    public String leaveBoard(String boardId){
        return "Attempting to leave game";
    }

    public String deleteBoard(String boardId){
        return "Method to delete board called. Would be nice if it was implemented";
    }

}
