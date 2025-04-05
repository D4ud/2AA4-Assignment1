package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeDirectionTest {

    @Test
    public void testTurnRight() {
        assertEquals(MazeDirection.SOUTH, MazeDirection.EAST.turnRight());
        assertEquals(MazeDirection.WEST, MazeDirection.SOUTH.turnRight());
        assertEquals(MazeDirection.NORTH, MazeDirection.WEST.turnRight());
        assertEquals(MazeDirection.EAST, MazeDirection.NORTH.turnRight());
    }

    @Test
    public void testTurnAround() {
        assertEquals(MazeDirection.WEST, MazeDirection.EAST.turnAround());
        assertEquals(MazeDirection.NORTH, MazeDirection.SOUTH.turnAround());
        assertEquals(MazeDirection.EAST, MazeDirection.WEST.turnAround());
        assertEquals(MazeDirection.SOUTH, MazeDirection.NORTH.turnAround());
    }
}