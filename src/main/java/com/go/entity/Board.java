package com.go.entity;

import org.springframework.data.annotation.Id;

public class Board {

    @Id
    private String id;
    
    private String creatorId;
    private String whitePlayerId;

    private String blackPlayerId;

    private int[][] boardState;  // values: 0 empty, 1 black, 2 white, 19x19 2D-array
    private boolean blackToMove;
    private int capturedBlack;
    private int capturedWhite;

    public  Board(String creatorId){
        //initialize the board with all zeros
        int[][] array = new int[19][19];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 0;
            }
        }
        this.setBoardState(array);
        this.setCreatorId(creatorId);
        //set creator to play balck and white by default
        this.setBlackPlayerId(creatorId);
        this.setWhitePlayerId(creatorId);
        this.setCapturedBlack(0);
        this.setCapturedWhite(0);
        this.setBlackToMove(true);

    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getWhitePlayerId() {
        return whitePlayerId;
    }

    public void setWhitePlayerId(String whitePlayerId) {
        this.whitePlayerId = whitePlayerId;
    }

    public String getBlackPlayerId() {
        return blackPlayerId;
    }

    public void setBlackPlayerId(String blackPlayerId) {
        this.blackPlayerId = blackPlayerId;
    }



    // getters and setters

    public String getId() {
        return id;
    }

    public int getCapturedBlack() {
        return capturedBlack;
    }

    public void setCapturedBlack(int capturedBlack) {
        this.capturedBlack = capturedBlack;
    }

    public int getCapturedWhite() {
        return capturedWhite;
    }

    public void setCapturedWhite(int capturedWhite) {
        this.capturedWhite = capturedWhite;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public int[][] getBoardState() {
        return boardState;
    }

    public void setBoardState(int[][] boardState) {
        this.boardState = boardState;
    }

    public boolean isBlackToMove() {
        return blackToMove;
    }

    public void setBlackToMove(boolean blackToMove) {
        this.blackToMove = blackToMove;
    }

    public void setSpot(int row, int col, int color){
        boardState[row][col] =  color;

    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println(); 
        }
    }
}
