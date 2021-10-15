package com.mars;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoverTest {

    @Test
    void le_rover_nest_pas_null() {
        // given, arrange
        final int x = 1;
        final int y = 2;
        final Direction direction = Direction.W;

        // when, act
        final Rover rover = Rover.create(x, y, direction);

        // then, assert
        assertThat(rover).isNotNull();
    }
}

