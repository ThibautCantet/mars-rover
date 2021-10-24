package com.mars.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Rover {
    private Detector detector;

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private int x;

    @Column
    private int y;

    private Coordinate coordinate;

    @Column
    private Direction direction;

    public Rover() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private Rover(UUID id, Detector detector, Coordinate coordinate, Direction direction) {
        this.id = id;
        this.detector = detector;
        this.coordinate = coordinate;
        this.direction = direction;
    }

    private Rover(Detector detector, Coordinate coordinate, Direction direction) {
        this(UUID.randomUUID(), detector, coordinate, direction);
    }

    public static Rover create(Detector detector, Coordinate coordinate, Direction direction) {
        return new Rover(detector, coordinate, direction);
    }

    public static Rover of(UUID id, Detector detector, Coordinate coordinate, Direction direction) {
        return new Rover(id, detector, coordinate, direction);
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
        if (detector.probe()) {
            return;
        }
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
        coordinate = updateCoordinate(y, x);
    }

    private Coordinate updateCoordinate(Integer y, Integer x) {
        final Coordinate coordinate = new Coordinate(x, y);
        this.x = coordinate.x();
        this.y = coordinate.y();
        return coordinate;
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
        coordinate = updateCoordinate(y, x);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rover rover = (Rover) o;
        return Objects.equals(detector, rover.detector) && Objects.equals(id, rover.id) && Objects.equals(coordinate, rover.coordinate) && direction == rover.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(detector, id, x, y, coordinate, direction);
    }
}
