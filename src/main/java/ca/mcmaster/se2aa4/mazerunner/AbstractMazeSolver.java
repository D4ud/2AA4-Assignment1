package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractMazeSolver implements MazeSolverAlgorithm {
    protected char[][] mazeGrid;
    protected MazeCoordinate currentPosition;
    protected MazeCoordinate exitPosition;
    protected MazeDirection currentDirection;
    protected List<String> movementPath;

    @Override
    public final List<String> computePath(char[][] mazeGrid, MazeCoordinate startCoord, MazeCoordinate exitCoord) {
        this.mazeGrid = mazeGrid;
        this.currentPosition = new MazeCoordinate(startCoord);
        this.exitPosition = new MazeCoordinate(exitCoord);
        this.currentDirection = MazeDirection.EAST;
        this.movementPath = new ArrayList<>();

        setup();

        while (!currentPosition.equalsTo(exitPosition)) {
            makeMove();
        }

        return movementPath;
    }

    protected void setup() {}

    // subclasses implement this
    protected abstract void makeMove();

    protected void moveForward() {
        movementPath.add("F");
        advancePosition(currentPosition, currentDirection);
    }

    protected void advancePosition(MazeCoordinate position, MazeDirection direction) {
        position.move(direction);
    }

    protected boolean isPositionAccessible(MazeCoordinate position) {
        int rowIndex = position.getRow();
        int columnIndex = position.getCol();
        return rowIndex >= 0 && rowIndex < mazeGrid.length &&
                columnIndex >= 0 && columnIndex < mazeGrid[0].length &&
                mazeGrid[rowIndex][columnIndex] != '#';
    }
}
