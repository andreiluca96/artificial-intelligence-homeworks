package com.company.problem;

public class Pawn {
    private int line;
    private int column;
    private boolean eaten;

    public Pawn(int line, int column) {
        this.line = line;
        this.column = column;
        this.eaten = false;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
}
