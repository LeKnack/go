package com.go.service;


import com.go.dto.BoardDto;
import com.go.entity.Board;
import com.go.view.BoardView;

public class BoardMappingService {

    private final UserMappingService userMappingService;

    public BoardMappingService(UserService userService, UserMappingService userMappingService) {
        this.userMappingService = userMappingService;
    }


    public static Board mapDtoToEntity(BoardDto boardDto) {
        Board board = new Board(boardDto.getCreatorId());
        board.setCreatorId(boardDto.getCreatorId());
        board.setWhitePlayerId(boardDto.getWhitePlayerId());
        board.setBlackPlayerId(boardDto.getBlackPlayerId());
        board.setBoardState(boardDto.getBoardState());
        board.setBlackToMove(boardDto.isBlackToMove());
        board.setCapturedBlack(boardDto.getCapturedBlack());
        board.setCapturedWhite(boardDto.getCapturedWhite());
        board.setId(boardDto.getId());
        return board;
    }

    public static BoardDto mapEntityToDto(Board board) {
        BoardDto boardDto =  new BoardDto();
        boardDto.setCreatorId(board.getCreatorId());
        boardDto.setWhitePlayerId(board.getWhitePlayerId());
        boardDto.setBlackPlayerId(board.getBlackPlayerId());
        boardDto.setBoardState(board.getBoardState());
        boardDto.setBlackToMove(board.isBlackToMove());
        boardDto.setCapturedBlack(board.getCapturedBlack());
        boardDto.setCapturedWhite(board.getCapturedWhite());
        boardDto.setId(board.getId());
        return boardDto;
    }

    public static BoardView mapDtoToView(BoardDto boardDto) {
        BoardView boardView = new BoardView();
        boardView.setCreatorId(boardDto.getCreatorId());
        //boardView.setWhitePlayerId(boardDto.getWhitePlayerId());
        //boardView.setBlackPlayerId(boardDto.getBlackPlayerId());
        boardView.setBoardState(boardDto.getBoardState());
        boardView.setBlackToMove(boardDto.isBlackToMove());
        boardView.setCapturedBlack(boardDto.getCapturedBlack());
        boardView.setCapturedWhite(boardDto.getCapturedWhite());
        boardView.setId(boardDto.getId());
        return boardView;
    }

    public static BoardDto mapViewToDto(BoardView boardView) {
        BoardDto boardDto =  new BoardDto();
        boardDto.setCreatorId(boardView.getCreatorId());
        boardDto.setBoardState(boardView.getBoardState());
        boardDto.setBlackToMove(boardView.isBlackToMove());
        boardDto.setCapturedBlack(boardView.getCapturedBlack());
        boardDto.setCapturedWhite(boardView.getCapturedWhite());
        boardDto.setId(boardView.getId());
        return boardDto;
    }


}
