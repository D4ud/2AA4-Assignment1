package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class MazeExplorer {
    private MazeMap mazeMap;
    private MazeSolverAlgorithm solverAlgorithm;

    public MazeExplorer(MazeMap mazeMap, MazeSolverAlgorithm solverAlgorithm) {
        this.mazeMap = mazeMap;
        this.solverAlgorithm = solverAlgorithm;
    }

    public String computeCompressedPath() {
        List<String> rawPath = solverAlgorithm.computePath(mazeMap.getMazeGrid(), new MazeCoordinate(mazeMap.getEntryCoordinate()), new MazeCoordinate(mazeMap.getExitCoordinate()));
        StringBuilder canonicalPath = new StringBuilder();
        for (String step : rawPath) {
            canonicalPath.append(step);
        }
        return compressInstructions(canonicalPath.toString());
    }

    private String compressInstructions(String instructions) {
        if (instructions == null || instructions.isEmpty()) {
            return "";
        }
        StringBuilder compressed = new StringBuilder();
        int count = 1;
        char previousChar = instructions.charAt(0);
        for (int index = 1; index < instructions.length(); index++) {
            char currentChar = instructions.charAt(index);
            if (currentChar == previousChar) {
                count++;
            } else {
                if (count > 1) {
                    compressed.append(count);
                }
                compressed.append(previousChar).append(" ");
                count = 1;
            }
            previousChar = currentChar;
        }
        if (count > 1) {
            compressed.append(count);
        }
        compressed.append(previousChar).append(" ");
        return compressed.toString();
    }

    public boolean validateProvidedPath(String pathInstructions) {
        MazeCoordinate currentCoordinate = new MazeCoordinate(mazeMap.getEntryCoordinate());
        MazeCoordinate exitCoordinate = mazeMap.getExitCoordinate();
        MazeDirection currentDirection = (currentCoordinate.getCol() == 0) ? MazeDirection.EAST : MazeDirection.WEST;
        int index = 0;
        while (index < pathInstructions.length()) {
            char currentChar = pathInstructions.charAt(index);
            int repeatCount = 1;
            if (Character.isDigit(currentChar)) {
                int startIndex = index;
                while (index < pathInstructions.length() && Character.isDigit(pathInstructions.charAt(index))) {
                    index++;
                }
                repeatCount = Integer.parseInt(pathInstructions.substring(startIndex, index));
                if (index >= pathInstructions.length()) {
                    break;
                }
                currentChar = pathInstructions.charAt(index);
            }
            switch (currentChar) {
                case 'F' -> {
                    for (int counter = 0; counter < repeatCount; counter++) {
                        currentCoordinate.move(currentDirection);
                        if (!mazeMap.isPositionValid(currentCoordinate)) {
                            return false;
                        }
                    }
                }
                case 'R' -> {
                    for (int counter = 0; counter < repeatCount; counter++) {
                        currentDirection = currentDirection.turnRight();
                    }
                }
                case 'L' -> {
                    for (int counter = 0; counter < repeatCount; counter++) {
                        currentDirection = currentDirection.turnLeft();
                    }
                }
            }
            index++;
        }
        return currentCoordinate.equalsTo(exitCoordinate);
    }
}
