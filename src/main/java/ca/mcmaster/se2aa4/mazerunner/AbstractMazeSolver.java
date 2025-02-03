package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public abstract class AbstractMazeSolver implements MazeSolverAlgorithm {
    protected char[][] mazeGrid;
    protected MazeCoordinate currentPosition;
    protected MazeCoordinate exitPosition;
    protected MazeDirection currentDirection;
    protected List<String> movementPath;

    protected void advancePosition(MazeCoordinate position, MazeDirection direction) {
        position.move(direction);
    }

    protected boolean isPositionAccessible(MazeCoordinate position) {
        int rowIndex = position.getRow();
        int columnIndex = position.getCol();
        return rowIndex >= 0 && rowIndex < mazeGrid.length && columnIndex >= 0 && columnIndex < mazeGrid[0].length && mazeGrid[rowIndex][columnIndex] != '#';
    }
}
