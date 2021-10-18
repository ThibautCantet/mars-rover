package com.mars;

public class Rover {
    private Coordinate coordinate;
    private Direction direction;

    private Rover(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public static Rover create(Coordinate coordinate, Direction direction) {
        return new Rover(coordinate, direction);
    }

    public Direction getDirection() {
        return direction;
    }

    public Integer getX() {
        return coordinate.x();
    }

    public Integer getY() {
        return coordinate.y();
    }

    public void move(Command command) {
        switch (command) {
            case FORWARD:
                moveForward();
                break;
            case BACKWARD:
                moveBackward();
                break;
            case LEFT:
                turnLeft();
                break;
            case RIGHT:
                turnRight();
        }
    }

    private void moveForward() {
        Integer y = coordinate.x();
        Integer x = coordinate.y();
        if (direction.equals(Direction.S)) {
            y--;
        } else if (direction.equals(Direction.W)) {
            x--;
        } else if (direction.equals(Direction.E)) {
            x++;
        } else {
            y++;
        }
        coordinate = new Coordinate(x, y);
    }

    private void moveBackward() {
        Integer y = coordinate.x();
        Integer x = coordinate.y();
        if (direction.equals(Direction.N)) {
            y--;
        } else if (direction.equals(Direction.W)) {
            x++;
        } else if (direction.equals(Direction.E)) {
            x--;
        } else {
            y++;
        }
        coordinate = new Coordinate(x, y);
    }

    private void turnLeft() {
        if (direction.equals(Direction.N)) {
            direction = Direction.W;
        } else if (direction.equals(Direction.W)) {
            direction = Direction.S;
        } else if (direction.equals(Direction.E)) {
            direction = Direction.N;
        } else {
            direction = Direction.E;
        }
    }

    private void turnRight() {
        if (direction.equals(Direction.N)) {
            direction = Direction.E;
        } else if (direction.equals(Direction.W)) {
            direction = Direction.N;
        }else if (direction.equals(Direction.E)) {
            direction = Direction.S;
        } else {
            direction = Direction.W;
        }
    }
}
