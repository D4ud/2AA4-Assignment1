package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RightHandMazeSolver extends AbstractMazeSolver {
    private Set<String> visitedStates;

    @Override
    protected void setup() {
        visitedStates = new HashSet<>();
    }

    @Override
    protected void makeMove() {
        String stateSignature = currentPosition.getRow() + "," + currentPosition.getCol() + "," + currentDirection;
        if (visitedStates.contains(stateSignature)) {
            return;
        }
        visitedStates.add(stateSignature);

        MazeCoordinate right = new MazeCoordinate(currentPosition);
        advancePosition(right, currentDirection.turnRight());
        MazeCoordinate front = new MazeCoordinate(currentPosition);
        advancePosition(front, currentDirection);
        MazeCoordinate left = new MazeCoordinate(currentPosition);
        advancePosition(left, currentDirection.turnLeft());

        if (isPositionAccessible(right)) {
            currentDirection = currentDirection.turnRight();
            movementPath.add("R");
            moveForward();
        } else if (isPositionAccessible(front)) {
            moveForward();
        } else if (isPositionAccessible(left)) {
            currentDirection = currentDirection.turnLeft();
            movementPath.add("L");
            moveForward();
        } else {
            currentDirection = currentDirection.turnAround();
            movementPath.add("RR");
        }
    }
}
