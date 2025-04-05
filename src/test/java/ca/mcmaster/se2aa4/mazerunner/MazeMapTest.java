package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeMapTest {

    @Test
    public void testEntryAndExitAreParsedCorrectly() {
        MazeMap map = TestMazeFactory.fromFile("src/test/resources/tiny.maz.txt");
        MazeCoordinate entry = map.getEntryCoordinate();
        MazeCoordinate exit = map.getExitCoordinate();

        assertNotNull(entry);
        assertNotNull(exit);
    }
}