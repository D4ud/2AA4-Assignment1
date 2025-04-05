package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RightHandMazeSolverTest {

    @Test
    public void testMazeSolvable() {
        MazeMap map = TestMazeFactory.fromFile("src/test/resources/tiny.maz.txt");
        MazeSolverAlgorithm solver = new RightHandMazeSolver();
        List<String> path = solver.computePath(map.getMazeGrid(), map.getEntryCoordinate(), map.getExitCoordinate());

        assertFalse(path.isEmpty());
        assertTrue(path.contains("F"));
    }
}