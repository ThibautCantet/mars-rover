package com.mars;

public class Rover {
    private final int x;
    private int y;
    private Direction direction;

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

    public void move(Command command) {
        switch (command) {
            case FORWARD:
                if (direction.equals(Direction.S)) {
                    y--;
                } else {
                    y++;
                }
                break;
            case BACKWARD:
                if (direction.equals(Direction.N)) {
                    y--;
                } else {
                    y++;
                }
                break;
            case LEFT:
                if (direction.equals(Direction.N)) {
                    direction = Direction.W;
                } else {
                    direction = Direction.E;
                }
                break;
            case RIGHT:
                direction = Direction.W;
        }
    }
}
