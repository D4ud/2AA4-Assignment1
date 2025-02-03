package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface MazeSolverAlgorithm {
    List<String> computePath(char[][] mazeGrid, MazeCoordinate startCoord, MazeCoordinate exitCoord);
}
