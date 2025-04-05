package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TremauxMazeSolver extends AbstractMazeSolver {
    private Map<String, Integer> pathVisits;

    @Override
    protected void setup() {
        pathVisits = new HashMap<>();
    }

    @Override
    protected void makeMove() {
        String coordinateKey = currentPosition.getRow() + "," + currentPosition.getCol();
        pathVisits.put(coordinateKey, pathVisits.getOrDefault(coordinateKey, 0) + 1);

        List<MazeDirection> available = new ArrayList<>();
        for (MazeDirection dir : MazeDirection.values()) {
            MazeCoordinate next = new MazeCoordinate(currentPosition);
            advancePosition(next, dir);
            if (isPositionAccessible(next)) {
                available.add(dir);
            }
        }

        if (!available.isEmpty()) {
            available.sort(Comparator.comparingInt(d -> getVisitCount(getNextCoordinate(d))));
            MazeDirection chosen = available.get(0);
            moveInDirection(chosen);
        } else {
            backtrack();
        }
    }

    private MazeCoordinate getNextCoordinate(MazeDirection direction) {
        MazeCoordinate next = new MazeCoordinate(currentPosition);
        advancePosition(next, direction);
        return next;
    }

    private int getVisitCount(MazeCoordinate coord) {
        String key = coord.getRow() + "," + coord.getCol();
        return pathVisits.getOrDefault(key, 0);
    }

    private void moveInDirection(MazeDirection newDirection) {
        if (newDirection != currentDirection) {
            int diff = (newDirection.ordinal() - currentDirection.ordinal() + 4) % 4;
            if (diff == 1) movementPath.add("R");
            else if (diff == 3) movementPath.add("L");
            else movementPath.add("RR");
            currentDirection = newDirection;
        }
        moveForward();
    }

    private void backtrack() {
        MazeDirection reverse = currentDirection.turnAround();
        MazeCoordinate previous = new MazeCoordinate(currentPosition);
        advancePosition(previous, reverse);
        if (isPositionAccessible(previous)) {
            moveInDirection(reverse);
        }
    }
}
