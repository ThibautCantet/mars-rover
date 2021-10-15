package com.mars;

public class Rover {
    private Direction direction;

    private Rover(Direction direction) {
        this.direction = direction;
    }

    public static Rover create(int x, int y, Direction direction) {
        return new Rover(direction);
    }

    public Direction getDirection() {
        return direction;
    }
}
