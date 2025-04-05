package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeCoordinateTest {

    @Test
    public void testCoordinateEquality() {
        MazeCoordinate coord1 = new MazeCoordinate(2, 3);
        MazeCoordinate coord2 = new MazeCoordinate(2, 3);
        assertTrue(coord1.equalsTo(coord2), "Coordinates should be equal");
    }

    @Test
    public void testCoordinateMovementEast() {
        MazeCoordinate coord = new MazeCoordinate(1, 1);
        coord.move(MazeDirection.EAST);
        assertEquals(2, coord.getCol());
        assertEquals(1, coord.getRow());
    }

    @Test
    public void testCoordinateMovementNorth() {
        MazeCoordinate coord = new MazeCoordinate(1, 1);
        coord.move(MazeDirection.NORTH);
        assertEquals(1, coord.getCol());
        assertEquals(0, coord.getRow());
    }
}