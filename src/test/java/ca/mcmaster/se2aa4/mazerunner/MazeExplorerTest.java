package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeExplorerTest {

    @Test
    public void testValidPath() {
        MazeMap map = TestMazeFactory.fromFile("src/test/resources/tiny.maz.txt");
        MazeExplorer explorer = new MazeExplorer(map, new RightHandMazeSolver());
        String path = explorer.computeCompressedPath();

        assertTrue(explorer.validateProvidedPath(path));
    }

    @Test
    public void testInvalidPath() {
        MazeMap map = TestMazeFactory.fromFile("src/test/resources/tiny.maz.txt");
        MazeExplorer explorer = new MazeExplorer(map, new RightHandMazeSolver());

        assertFalse(explorer.validateProvidedPath("R"));
    }
}