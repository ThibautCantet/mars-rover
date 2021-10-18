package com.mars;

public record Coordinate(Integer x, Integer y) {
    public static Integer PLANET_SIZE = 5;

    public Coordinate(Integer x, Integer y) {
        if (x < 0) {
            this.x = PLANET_SIZE - 1;
        } else {
            this.x = x;
        }
        if (y < 0) {
            this.y = PLANET_SIZE - 1;
        } else {
            this.y = y;
        }
    }
}
