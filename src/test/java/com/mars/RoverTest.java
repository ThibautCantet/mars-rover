package com.mars;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RoverTest {
    private static final int x = 1;
    private static final int y = 1;

    @Nested
    static class Create {

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

        @ParameterizedTest
        @MethodSource("provideCoordonnees")
        void le_rover_est_positionne_aux_coordonnees_initiales(Integer x, Integer y) {
            // when, act
            final Rover rover = Rover.create(x, y, Direction.W);

            // then, assert
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }

        private static Stream<Arguments> provideCoordonnees() {
            return Stream.of(Arguments.of(1, 2), Arguments.of(2, 3));
        }

    }

    @Test
    void move_forward_doit_modifier_coordonnee_y_a_2_quand_la_position_initiale_est_1_1_S() {
        // given
        final Rover rover = Rover.create(x, y, Direction.S);

        // when
        rover.move(Command.FORWARD);

        // then
        assertThat(rover.getY()).isEqualTo(2);
    }

    @Test
    void move_backward_doit_modifier_coordonnee_y_a_0_quand_la_position_initiale_est_1_1_S() {
        // given
        final Rover rover = Rover.create(x, y, Direction.S);

        // when
        rover.move(Command.BACKWARD);

        // then
        assertThat(rover.getY()).isZero();
    }
}

