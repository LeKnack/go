package com.go.service;

import com.go.dto.MoveDto;
import com.go.entity.Move;

public class MoveMappingService {
    public static MoveDto entityToDto(Move move){
        MoveDto moveDto = new MoveDto();
        moveDto.setCol(move.getCol());
        moveDto.setRow(move.getRow());
        moveDto.setColor(move.getColor());
        return moveDto;
    }
    public static Move dtoToEntity(MoveDto moveDto){
        Move move = new Move();
        move.setCol(moveDto.getCol());
        move.setRow(moveDto.getRow());
        move.setColor(moveDto.getColor());
        return move;
    }
}
