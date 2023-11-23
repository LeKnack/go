package com.go.view;

public class BoardView {

    private String creatorId;
    private String id;
    private int[][] boardState;
    private boolean blackToMove;
    private int capturedBlack;
    private int capturedWhite;

    
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }


}
