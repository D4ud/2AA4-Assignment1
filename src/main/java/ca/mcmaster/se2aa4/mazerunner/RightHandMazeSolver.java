package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RightHandMazeSolver extends AbstractMazeSolver {
    public List<String> computePath(char[][] mazeGrid, MazeCoordinate startCoord, MazeCoordinate exitCoord) {
        this.mazeGrid = mazeGrid;
        this.currentPosition = new MazeCoordinate(startCoord);
        this.exitPosition = new MazeCoordinate(exitCoord);
        this.currentDirection = MazeDirection.EAST;
        this.movementPath = new ArrayList<>();
        Set<String> visitedStates = new HashSet<>();
        while (!currentPosition.equalsTo(exitPosition)) {
            String stateSignature = currentPosition.getRow() + "," + currentPosition.getCol() + "," + currentDirection;
            if (visitedStates.contains(stateSignature)) {
                break;
            }
            visitedStates.add(stateSignature);
            MazeCoordinate rightCoordinate = new MazeCoordinate(currentPosition);
            advancePosition(rightCoordinate, currentDirection.turnRight());
            MazeCoordinate frontCoordinate = new MazeCoordinate(currentPosition);
            advancePosition(frontCoordinate, currentDirection);
            MazeCoordinate leftCoordinate = new MazeCoordinate(currentPosition);
            advancePosition(leftCoordinate, currentDirection.turnLeft());
            if (isPositionAccessible(rightCoordinate)) {
                currentDirection = currentDirection.turnRight();
                movementPath.add("R");
                moveForward();
            } else if (isPositionAccessible(frontCoordinate)) {
                moveForward();
            } else if (isPositionAccessible(leftCoordinate)) {
                currentDirection = currentDirection.turnLeft();
                movementPath.add("L");
                moveForward();
            } else {
                currentDirection = currentDirection.turnAround();
                movementPath.add("RR");
            }
        }
        return movementPath;
    }

    private void moveForward() {
        movementPath.add("F");
        advancePosition(currentPosition, currentDirection);
    }
}
