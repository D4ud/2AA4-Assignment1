package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeMap {
    private char[][] mazeGrid;
    private MazeCoordinate entryCoordinate;
    private MazeCoordinate exitCoordinate;

    public MazeMap(String filePath) {
        List<String> rows = new ArrayList<>();
        int maxColumns = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                rows.add(currentLine);
                if (currentLine.length() > maxColumns) {
                    maxColumns = currentLine.length();
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Error reading maze file: " + ioException.getMessage());
        }
        if (rows.isEmpty()) {
            throw new RuntimeException("Maze file is empty.");
        }
        int rowCount = rows.size();
        mazeGrid = new char[rowCount][maxColumns];
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            String currentRow = rows.get(rowIndex);
            for (int columnIndex = 0; columnIndex < maxColumns; columnIndex++) {
                mazeGrid[rowIndex][columnIndex] = (columnIndex < currentRow.length()) ? currentRow.charAt(columnIndex) : ' ';
            }
        }
        computeEntryExitCoordinates();
    }

    private void computeEntryExitCoordinates() {
        MazeCoordinate entry = null;
        MazeCoordinate exit = null;
        for (int rowIndex = 0; rowIndex < mazeGrid.length; rowIndex++) {
            if (mazeGrid[rowIndex][0] == ' ') {
                entry = new MazeCoordinate(rowIndex, 0);
            }
            if (mazeGrid[rowIndex][mazeGrid[rowIndex].length - 1] == ' ') {
                exit = new MazeCoordinate(rowIndex, mazeGrid[rowIndex].length - 1);
            }
        }
        if (entry == null || exit == null) {
            throw new RuntimeException("Maze entry or exit not found.");
        }
        entryCoordinate = entry;
        exitCoordinate = exit;
    }

    public char[][] getMazeGrid() {
        return mazeGrid;
    }

    public MazeCoordinate getEntryCoordinate() {
        return entryCoordinate;
    }

    public MazeCoordinate getExitCoordinate() {
        return exitCoordinate;
    }

    public boolean isPositionValid(MazeCoordinate coordinate) {
        int rowIndex = coordinate.getRow();
        int columnIndex = coordinate.getCol();
        return rowIndex >= 0 && rowIndex < mazeGrid.length && columnIndex >= 0 && columnIndex < mazeGrid[0].length && mazeGrid[rowIndex][columnIndex] != '#';
    }
}
