package ca.mcmaster.se2aa4.mazerunner;

public class MazeCoordinate {
    private int row;
    private int col;

    public MazeCoordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public MazeCoordinate(MazeCoordinate other) {
        this.row = other.row;
        this.col = other.col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move(MazeDirection direction) {
        switch (direction) {
            case EAST -> col++;
            case SOUTH -> row++;
            case WEST -> col--;
            case NORTH -> row--;
        }
    }

    public boolean equalsTo(MazeCoordinate other) {
        return row == other.row && col == other.col;
    }
}
