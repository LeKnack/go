package com.go.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;


public class Board {

    @Id
    private String id;
    
    private String creatorId;
    private String whitePlayerId;

    private String blackPlayerId;

    private int[][] boardState;  // values: 0 empty, 1 black, 2 white, 19x19 2D-array
    
    private int[][] prevBoardState;
    private int[][] preprevBoardState;

    private boolean blackToMove;
    private int capturedBlack;
    private int capturedWhite;

    public  Board(){
        //initialize the board with all zeros
        int[][] arrayPrePrev = new int[19][19];
        int[][] arrayOld = new int[19][19];
        int[][] array = new int[19][19];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 0;
            }
        }
        this.setBoardState(array);
        this.setPrevBoardState(arrayOld);
        this.setPreprevBoardState(arrayPrePrev);
        this.setCreatorId("0");
        //set creator to play balck and white by default
        this.setBlackPlayerId(creatorId);
        this.setWhitePlayerId(creatorId);
        this.setCapturedBlack(0);
        this.setCapturedWhite(0);
        this.setBlackToMove(true);

        this.id = UUID.randomUUID().toString();
    }

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
        //set creator to play black and white by default
        this.setBlackPlayerId(creatorId);
        this.setWhitePlayerId(creatorId);
        this.setCapturedBlack(0);
        this.setCapturedWhite(0);
        this.setBlackToMove(true);
        
        this.id = UUID.randomUUID().toString();
    }

    public int[][] getPrevBoardState() {
        return prevBoardState;
    }

    public void setPrevBoardState(int[][] prevBoardState) {
        this.prevBoardState = prevBoardState;
    }

    public int[][] getPreprevBoardState() {
        return preprevBoardState;
    }

    public void setPreprevBoardState(int[][] preprevBoardState) {
        this.preprevBoardState = preprevBoardState;
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
    
    private static boolean containsPoint(List<int[]> list, int row, int col) {
        for (int[] point : list) {
            if (point[0] == row && point[1] == col) {
                return true;
            }
        }
        return false;
    }


    public  List<int[]> findGroup( int row, int col) {
        int targetColor = boardState[row][col];
        boolean[][] visited = new boolean[boardState.length][boardState[0].length];
        List<int[]> group = new ArrayList<>();

        searchNeighbors(row, col, targetColor, visited, group);

        return group;
    }

    private  void searchNeighbors( int row, int col, int targetColor, boolean[][] visited, List<int[]> connectedGroup) {
        if (row < 0 || row >= boardState.length || col < 0 || col >= boardState[0].length || visited[row][col] || boardState[row][col] != targetColor) {
            return;
        }

        visited[row][col] = true;
        connectedGroup.add(new int[]{row, col});

        // Check neighboring points
        searchNeighbors(row - 1, col, targetColor, visited, connectedGroup); // Up
        searchNeighbors(row + 1, col, targetColor, visited, connectedGroup); // Down
        searchNeighbors(row, col - 1, targetColor, visited, connectedGroup); // Left
        searchNeighbors(row, col + 1, targetColor, visited, connectedGroup); // Right
    }
    private  boolean checkColor( int row, int col, int targetColor) {
        if (row < 0 || row >= boardState.length || col < 0 || col >= boardState[0].length || boardState[row][col] != targetColor) {
            return false;
        }
        return true;
    }

    public boolean checkLiberty( List<int[]> group){
        for(int[] point : group){
            int row = point[0];
            int col = point[1];
            if(checkColor(row, col, 0)
                || checkColor(row+1, col, 0)
                || checkColor(row-1, col, 0)
                || checkColor(row, col+1, 0)
                || checkColor(row, col-1, 0)){
                    return true;
                }
        }
        return false;
    }
    //might be useful
    public static int complementColor(int color){
        if( color  == 0){
            return 0;
        }
        else{
            return (color % 2) + 1;
        }
    }

    private static boolean areArraysIdentical(int[][] array1, int[][] array2) {
        if (array1.length != array2.length || array1[0].length != array2[0].length) {
            return false; 
        }

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                if (array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }

        return true; 
    }

    public static void printBoardState(int[][] boardState) {
        for (int[] row : boardState) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Capture Logic fro Go

    public boolean updateBoard(Move move){

        System.out.println(String.format("Move: %d stone at (%d, %d)", move.getColor(), move.getRow(), move.getCol())); 
        //check the validity of the moves Color
        if((move.getColor()==1 && !(blackToMove)) || (move.getColor()==2) && blackToMove ){
            System.out.println("Nout your Turn, Black to Play: " + blackToMove);
            return false;
        }
        //check validity of the moves coordinates
        if(move.getCol()<0 || move.getCol()>boardState[0].length -1
            || move.getRow()<0 || move.getRow()>boardState.length-1){
                return false;
            }
        if( (!(move.getColor() == 1)) && (!(move.getColor()==2))){
            return false;
        }
        //save old state
        int[][] oldState = new int[boardState.length][boardState[0].length];
        for(int i=0; i < boardState.length; i++){
            System.arraycopy(boardState[i], 0, oldState[i], 0, boardState[0].length);
        }
        //make the move 
        boardState[move.getRow()][move.getCol()] = move.getColor();



        List<int[]> captured = new ArrayList<>();
        List<int[]> toCapture = new ArrayList<>();
        //check all adjacent groups
        //(row+1,col)
        if(checkColor(move.getRow()+1, move.getCol(), complementColor(move.getColor()))){
            toCapture = new ArrayList<>(findGroup(move.getRow()+1, move.getCol()));
            if( !(checkLiberty(toCapture))){
                captured.addAll(toCapture);
            }
        }
        //(row-1,col)
        if(checkColor(move.getRow()-1, move.getCol(), complementColor(move.getColor()))
            && !(containsPoint(toCapture, move.getRow()-1, move.getCol()))){
            toCapture = new ArrayList<>(findGroup(move.getRow()-1, move.getCol()));
            if( !(checkLiberty(toCapture))){
                captured.addAll(toCapture);
            }
        }
        //(row,col+1)
        if(checkColor(move.getRow(), move.getCol()+1, complementColor(move.getColor()))
            && !(containsPoint(toCapture, move.getRow(), move.getCol()+1))){
            toCapture = new ArrayList<>(findGroup(move.getRow(), move.getCol()+1));
            if( !(checkLiberty(toCapture))){
                captured.addAll(toCapture);
            }
        }   
        //(row,col-1)
        if(checkColor(move.getRow(), move.getCol()-1, complementColor(move.getColor()))
            && !(containsPoint(toCapture, move.getRow(), move.getCol()-1))){
            toCapture = new ArrayList<>(findGroup(move.getRow(), move.getCol()-1));
            if( !(checkLiberty(toCapture))){
                captured.addAll(toCapture);
            }
        }
        System.out.println("Done checking neighbors");
        for (int[] element : toCapture) {
            System.out.println(Arrays.toString(element));
        }
        System.out.println("Done checking print captured");
        for (int[] element : captured) {
            System.out.println(Arrays.toString(element));
        }

        //save previous and pre-previuous boardstate
        //can i reassign the reference here?
        preprevBoardState = prevBoardState;
        prevBoardState = oldState;
        System.out.println("oldstate");
        printBoardState(prevBoardState);

        System.out.println("preprevboardstate");
        printBoardState(preprevBoardState);
        
        if(captured.size()>0){
            System.out.println("Capturing");
            //Remove Captured Stones
            for (int[] point : captured) {
                boardState[point[0]][point[1]] = 0;
            }

            //check the Ko-Rule
            if(areArraysIdentical(boardState, preprevBoardState)){
                //take back move
                for(int i=0; i < boardState.length; i++){
                System.arraycopy(oldState[i], 0, oldState[i], 0, oldState[0].length);
                return false;
            }
            }

            //add captured stones to attributes
            if(move.getColor()==1){
                capturedBlack = capturedBlack+captured.size();
            }
            if(move.getColor()==2){
                    capturedWhite = capturedWhite+captured.size();
            }
        }

        System.out.println("move made");
        printBoardState(boardState);

        if(move.getColor()==1){
            blackToMove=false;
        }
        if(move.getColor()==2){
            blackToMove=true;
        }
        
        //since this was a valid move 
        return true;

    }
}
