package com.company.problem;

public class Pawn {
    private int line;
    private int column;

    public Pawn(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public Pawn(Pawn pawn) {
        this.line = pawn.line;
        this.column = pawn.column;
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

    @Override
    public String toString() {
        return "Pawn{" +
                "line=" + line +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pawn pawn = (Pawn) o;

        if (line != pawn.line) return false;
        return column == pawn.column;
    }

    @Override
    public int hashCode() {
        int result = line;
        result = 31 * result + column;
        return result;
    }
}
