package com.go;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.go.dto.MoveDto;
import com.go.entity.Board;
import com.go.entity.BoardRepository;
import com.go.entity.Move;
import com.go.service.BoardService;

@SpringBootTest
public class TestModel {

    private void printBoardState(int[][] boardState) {
        for (int[] row : boardState) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    void testCapture() {
        Board board = new Board();

        List<int[]> moves = new ArrayList<>();
        moves.add(new int[]{0, 1, 1});
        moves.add(new int[]{0, 0, 2});
        moves.add(new int[]{1, 0, 1});        
        // Set up a mock board for the service
        for(int[] moveData : moves){
            Move move =  new Move();
            move.setRow(moveData[0]);
            move.setCol(moveData[1]);
            move.setColor(moveData[2]);
            board.updateBoard(move);
        }
        int[][] boardState = board.getBoardState();
        printBoardState(boardState);
        assertEquals(0, boardState[0][0]);
        assertEquals(1, boardState[1][0]);
        assertEquals(1, boardState[0][1]);

    }
    
}
