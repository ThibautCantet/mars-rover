package com.mars;

public class Rover {
    private final int x;
    private final int y;
    private final Direction direction;

    private Rover(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public static Rover create(int x, int y, Direction direction) {
        return new Rover(x, y, direction);
    }

    public Direction getDirection() {
        return direction;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
