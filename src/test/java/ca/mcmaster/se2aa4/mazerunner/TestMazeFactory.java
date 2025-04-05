package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TestMazeFactory {

    public static MazeMap fromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filename));
            int rows = lines.size();
            int cols = lines.stream().mapToInt(String::length).max().orElse(0);
            char[][] grid = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = lines.get(i);
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = j < line.length() ? line.charAt(j) : ' ';
                }
            }

            return new MazeMap(grid);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test maze file: " + filename);
        }
    }
}