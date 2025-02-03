package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TremauxMazeSolver extends AbstractMazeSolver {
    private Map<String, Integer> pathVisits;

    public List<String> computePath(char[][] mazeGrid, MazeCoordinate startCoord, MazeCoordinate exitCoord) {
        this.mazeGrid = mazeGrid;
        this.currentPosition = new MazeCoordinate(startCoord);
        this.exitPosition = new MazeCoordinate(exitCoord);
        this.currentDirection = MazeDirection.EAST;
        this.movementPath = new ArrayList<>();
        this.pathVisits = new HashMap<>();
        while (!currentPosition.equalsTo(exitPosition)) {
            String coordinateKey = currentPosition.getRow() + "," + currentPosition.getCol();
            pathVisits.put(coordinateKey, pathVisits.getOrDefault(coordinateKey, 0) + 1);
            List<MazeDirection> availableDirections = new ArrayList<>();
            for (MazeDirection direction : MazeDirection.values()) {
                MazeCoordinate nextCoordinate = new MazeCoordinate(currentPosition);
                advancePosition(nextCoordinate, direction);
                if (isPositionAccessible(nextCoordinate)) {
                    availableDirections.add(direction);
                }
            }
            if (!availableDirections.isEmpty()) {
                availableDirections.sort(Comparator.comparingInt(dir -> getVisitCount(getNextCoordinate(dir))));
                MazeDirection chosenDirection = availableDirections.get(0);
                moveInDirection(chosenDirection);
            } else {
                backtrack();
            }
        }
        return movementPath;
    }

    private MazeCoordinate getNextCoordinate(MazeDirection direction) {
        MazeCoordinate nextCoordinate = new MazeCoordinate(currentPosition);
        advancePosition(nextCoordinate, direction);
        return nextCoordinate;
    }

    private int getVisitCount(MazeCoordinate coordinate) {
        String key = coordinate.getRow() + "," + coordinate.getCol();
        return pathVisits.getOrDefault(key, 0);
    }

    private void moveInDirection(MazeDirection newDirection) {
        if (newDirection != currentDirection) {
            int difference = (newDirection.ordinal() - currentDirection.ordinal() + 4) % 4;
            if (difference == 1) {
                movementPath.add("R");
            } else if (difference == 3) {
                movementPath.add("L");
            } else {
                movementPath.add("RR");
            }
            currentDirection = newDirection;
        }
        movementPath.add("F");
        advancePosition(currentPosition, currentDirection);
    }

    private void backtrack() {
        while (true) {
            String coordinateKey = currentPosition.getRow() + "," + currentPosition.getCol();
            if (pathVisits.getOrDefault(coordinateKey, 0) < 2) {
                break;
            }
            MazeDirection reverseDirection = currentDirection.turnAround();
            MazeCoordinate previousCoordinate = new MazeCoordinate(currentPosition);
            advancePosition(previousCoordinate, reverseDirection);
            if (isPositionAccessible(previousCoordinate)) {
                moveInDirection(reverseDirection);
                return;
            }
        }
    }
}
