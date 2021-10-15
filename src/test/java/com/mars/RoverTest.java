package com.mars;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RoverTest {

    @Nested
    static class Create {
        private final int x = 1;
        private final int y = 2;

        @Test
        void le_rover_nest_pas_null() {
            // when, act
            final Rover rover = Rover.create(x, y, Direction.W);

            // then, assert
            assertThat(rover).isNotNull();
        }

        @ParameterizedTest
        @MethodSource("provideDirections")
        void le_rover_est_oriente_vers_sa_direction_initiale(Direction direction) {
            // when, act
            final Rover rover = Rover.create(x, y, direction);

            // then, assert
            assertThat(rover.getDirection()).isEqualTo(direction);
        }

        private static Stream<Direction> provideDirections() {
            return Stream.of(Direction.values());
        }
    }
}

