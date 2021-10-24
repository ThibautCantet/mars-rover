package com.mars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Mars {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private int x;

    @Column
    private int y;

    @Column(name = "obstacle")
    private boolean isAnObstacle;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAnObstacle() {
        return isAnObstacle;
    }

    public void setAnObstacle(boolean anObstacle) {
        isAnObstacle = anObstacle;
    }
}
