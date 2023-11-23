package com.go;

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
import com.go.service.BoardService;

@SpringBootTest
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;  // Assuming you have a repository for the board

    private void printBoardState(int[][] boardState) {
        for (int[] row : boardState) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    @Test
    void testMultipleMoves() {
    }
}
