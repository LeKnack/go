package com.go.dto;

public class BoardDto {

    private String id;
    private String creatorId;
    private String whitePlayerId;
    private String blackPlayerId;
    private int[][] boardState;
    private boolean blackToMove;
    private int capturedBlack;
    private int capturedWhite;
        
    // getters and setters

 

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
        this.boardState[row][col] = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
