package ca.mcmaster.se2aa4.mazerunner;

public enum MazeDirection {
    EAST, SOUTH, WEST, NORTH;

    public MazeDirection turnRight() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public MazeDirection turnLeft() {
        return values()[(this.ordinal() + values().length - 1) % values().length];
    }

    public MazeDirection turnAround() {
        return values()[(this.ordinal() + 2) % values().length];
    }
}
