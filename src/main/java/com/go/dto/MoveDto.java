package com.go.dto;

public class MoveDto {
    private int row;
    private int col;
    private int color; //0 is black 1 is white


    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return "MoveDto{" +
                "row=" + row +
                ", col=" + col +
                ", color=" + color +
                '}';
    }
}
