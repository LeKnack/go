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
        System.out.println("#######testCapture########");
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
        assertEquals(1, board.getCapturedBlack());

    }
    @Test
    void testCaptureWhite() {
        System.out.println("#######testCaptureWhite########");
        Board board = new Board();

        List<int[]> moves = new ArrayList<>();
        moves.add(new int[]{3, 5, 1});
        moves.add(new int[]{0, 1, 2});
        moves.add(new int[]{0, 0, 1});
        moves.add(new int[]{1, 0, 2});        
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
        assertEquals(2, boardState[1][0]);
        assertEquals(2, boardState[0][1]);
        assertEquals(1, board.getCapturedWhite());

    }
    @Test
    void testKo() {
        Board board = new Board();
        System.out.println("#######testKo########");

        List<int[]> moves = new ArrayList<>();
        moves.add(new int[]{0, 1, 1});
        moves.add(new int[]{2, 0, 2});
        moves.add(new int[]{1, 0, 1});
        moves.add(new int[]{2, 2, 2});
        moves.add(new int[]{1, 2, 1});   
        moves.add(new int[]{3, 1, 2});
        moves.add(new int[]{2, 1, 1});  
        moves.add(new int[]{1, 1, 2});   
        moves.add(new int[]{2, 1, 1}); 
        // Set up a mock board for the service
        for(int[] moveData : moves){
            Move move =  new Move();
            move.setRow(moveData[0]);
            move.setCol(moveData[1]);
            move.setColor(moveData[2]);
            if(!(board.updateBoard(move))){
                System.out.println("Illegal Move");
            };
        }
        int[][] boardState = board.getBoardState();
        printBoardState(boardState);
        assertEquals(0, board.getCapturedBlack());
        assertEquals(1, board.getCapturedWhite());

    }
    
}
