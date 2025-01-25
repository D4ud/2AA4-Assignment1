package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeWalker {
    private static final Logger logger = LogManager.getLogger();
    private char[][] maze;
    private int startRow = -1;
    private int endRow = -1;

    public MazeWalker(String filePath) {
        readMaze(filePath);
        findStartAndEndPoints();
    }

    public void readMaze(String filePath) {
        List<String> mazeRows = new ArrayList<>();
        int maxCols = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                mazeRows.add(line);
                maxCols = Math.max(maxCols, line.length());
            }
            if (mazeRows.isEmpty()) {
                throw new IllegalStateException("/!\\ The maze is empty. /!\\");
            }

            int rows = mazeRows.size();
            maze = new char[rows][maxCols];

            for (int i = 0; i < rows; i++) {
                String row = mazeRows.get(i);
                for (int j = 0; j < maxCols; j++) {
                    maze[i][j] = (j < row.length()) ? row.charAt(j) : ' ';
                }
            }
        } catch (IOException e) {
            logger.error("/!\\ An error occurred while reading the maze file /!\\");
        }
    }

    private void findStartAndEndPoints() {
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == ' ') {
                startRow = i;
            }
            if (maze[i][maze[i].length - 1] == ' ') {
                endRow = i;
            }
        }

        if (startRow == -1 || endRow == -1) {
            logger.error("/!\\ No valid start/end points found in the maze. /!\\");
            throw new IllegalStateException("Missing start or end points.");
        }

        logger.info("Start point identified at ({}, 0)", startRow);
        logger.info("End point identified at ({}, {})", endRow, maze[0].length - 1);
    }

    public boolean validatePath(String inputtedPath) {
        Position currentPosition = new Position(startRow, 0);
        Position finalPosition = new Position(endRow, maze[0].length - 1);
        int direction = 0;

        for (int i = 0; i < inputtedPath.length(); i++) {
            char step = inputtedPath.charAt(i);
            switch (step) {
                case 'F':
                    if (direction % 4 == 0) {
                        currentPosition.moveForward();
                    } else if (direction % 4 == 1) {
                        currentPosition.moveRight();
                    } else if (direction % 4 == 2) {
                        currentPosition.moveBack();
                    } else {
                        currentPosition.moveLeft();
                    }
                    if (!isMoveValid(currentPosition)) {
                        logger.error("/!\\ Invalid move at ({}, {}) /!\\", currentPosition.row, currentPosition.col);
                        return false;
                    }
                    break;
                case 'R':
                    direction = (direction + 1) % 4;
                    break;
                case 'L':
                    direction = (direction - 1 + 4) % 4;
                    break;
                default:
                    logger.error("/!\\ Invalid character in path: {} /!\\", step);
                    return false;
            }
        }

        return currentPosition.equalsEachOther(finalPosition);
    }

    private boolean isMoveValid(Position position) {
        return position.row >= 0 && position.row < maze.length &&
                position.col >= 0 && position.col < maze[0].length &&
                maze[position.row][position.col] != '#';
    }

    public char[][] getMaze() {
        return maze;
    }

    static class Position {
        int row, col;
        Position(int r, int c) {
            this.row = r;
            this.col = c;
        }
        void moveForward() {
            col += 1;
        }
        void moveRight() {
            row += 1;
        }
        void moveLeft() {
            row -= 1;
        }
        void moveBack() {
            col -= 1;
        }
        public boolean equalsEachOther(Position otherPosition) {
            return this.row == otherPosition.row && this.col == otherPosition.col;
        }
    }
}
