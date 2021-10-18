package com.mars;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RoverTest {
    private static final int x = 0;
    private static final int y = 0;

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

    @Nested
    class WhenDirectionIsSouth {
        private final Direction SOUTH = Direction.S;

        @Test
        void move_forward_doit_modifier_coordonnee_y_a_2_quand_la_position_initiale_est_0_0_S() {
            // given
            final Rover rover = Rover.create(x, y, SOUTH);

            // when
            rover.move(Command.FORWARD);

            // then
            assertThat(rover.getY()).isEqualTo(-1);
        }

        @Test
        void move_backward_doit_modifier_coordonnee_y_a_0_quand_la_position_initiale_est_0_0_S() {
            // given
            final Rover rover = Rover.create(x, y, SOUTH);

            // when
            rover.move(Command.BACKWARD);

            // then
            assertThat(rover.getY()).isEqualTo(1);
        }

        @Test
        void turn_left_doit_modifier_la_direction_mais_pas_les_coordonnees_quand_la_position_initiale_est_0_0_S() {
            // given
            final Rover rover = Rover.create(x, y, SOUTH);

            // when
            rover.move(Command.LEFT);

            // then
            assertThat(rover.getDirection()).isEqualTo(Direction.E);
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }

        @Test
        void turn_right_doit_modifier_la_direction_mais_pas_les_coordonnees_quand_la_position_initiale_est_0_0_S() {
            // given
            final Rover rover = Rover.create(x, y, SOUTH);

            // when
            rover.move(Command.RIGHT);

            // then
            assertThat(rover.getDirection()).isEqualTo(Direction.W);
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }
    }

    @Nested
    class WhenDirectionIsNorth {
        private final Direction NORTH = Direction.N;

        @Test
        void move_forward_doit_modifier_coordonnee_y_a_2_quand_la_position_initiale_est_0_0_N() {
            // given
            final Rover rover = Rover.create(x, y, NORTH);

            // when
            rover.move(Command.FORWARD);

            // then
            assertThat(rover.getY()).isEqualTo(1);
        }

        @Test
        void move_backward_doit_modifier_coordonnee_y_a_0_quand_la_position_initiale_est_0_0_N() {
            // given
            final Rover rover = Rover.create(x, y, NORTH);

            // when
            rover.move(Command.BACKWARD);

            // then
            assertThat(rover.getY()).isEqualTo(-1);
        }

        @Test
        void turn_left_doit_modifier_la_direction_mais_pas_les_coordonnees_quand_la_position_initiale_est_0_0_N() {
            // given
            final Rover rover = Rover.create(x, y, NORTH);

            // when
            rover.move(Command.LEFT);

            // then
            assertThat(rover.getDirection()).isEqualTo(Direction.W);
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }

        @Test
        void turn_right_doit_modifier_la_direction_mais_pas_les_coordonnees_quand_la_position_initiale_est_0_0_N() {
            // given
            final Rover rover = Rover.create(x, y, NORTH);

            // when
            rover.move(Command.RIGHT);

            // then
            assertThat(rover.getDirection()).isEqualTo(Direction.E);
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }
    }

    @Nested
    class WhenDirectionIsWest {
        private final Direction WEST = Direction.W;

        @Test
        void move_forward_doit_modifier_coordonnee_y_a_2_quand_la_position_initiale_est_0_0_W() {
            // given
            final Rover rover = Rover.create(x, y, WEST);

            // when
            rover.move(Command.FORWARD);

            // then
            assertThat(rover.getX()).isEqualTo(-1);
        }

        @Test
        void move_backward_doit_modifier_coordonnee_y_a_0_quand_la_position_initiale_est_0_0_W() {
            // given
            final Rover rover = Rover.create(x, y, WEST);

            // when
            rover.move(Command.BACKWARD);

            // then
            assertThat(rover.getX()).isEqualTo(1);
        }

        @Test
        void turn_left_doit_modifier_la_direction_mais_pas_les_coordonnees_quand_la_position_initiale_est_0_0_W() {
            // given
            final Rover rover = Rover.create(x, y, WEST);

            // when
            rover.move(Command.LEFT);

            // then
            assertThat(rover.getDirection()).isEqualTo(Direction.S);
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }

        @Test
        void turn_right_doit_modifier_la_direction_mais_pas_les_coordonnees_quand_la_position_initiale_est_0_0_W() {
            // given
            final Rover rover = Rover.create(x, y, WEST);

            // when
            rover.move(Command.RIGHT);

            // then
            assertThat(rover.getDirection()).isEqualTo(Direction.N);
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }
    }
    @Nested
    class WhenDirectionIsEast {
        private final Direction EAST = Direction.E;

        @Test
        void move_forward_doit_modifier_coordonnee_y_a_2_quand_la_position_initiale_est_0_0_E() {
            // given
            final Rover rover = Rover.create(x, y, EAST);

            // when
            rover.move(Command.FORWARD);

            // then
            assertThat(rover.getX()).isEqualTo(1);
        }

        @Test
        void move_backward_doit_modifier_coordonnee_y_a_0_quand_la_position_initiale_est_0_0_E() {
            // given
            final Rover rover = Rover.create(x, y, EAST);

            // when
            rover.move(Command.BACKWARD);

            // then
            assertThat(rover.getX()).isEqualTo(-1);
        }

        @Test
        void turn_left_doit_modifier_la_direction_mais_pas_les_coordonnees_quand_la_position_initiale_est_0_0_E() {
            // given
            final Rover rover = Rover.create(x, y, EAST);

            // when
            rover.move(Command.LEFT);

            // then
            assertThat(rover.getDirection()).isEqualTo(Direction.N);
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }

        @Test
        void turn_right_doit_modifier_la_direction_mais_pas_les_coordonnees_quand_la_position_initiale_est_0_0_E() {
            // given
            final Rover rover = Rover.create(x, y, EAST);

            // when
            rover.move(Command.RIGHT);

            // then
            assertThat(rover.getDirection()).isEqualTo(Direction.S);
            assertThat(rover.getX()).isEqualTo(x);
            assertThat(rover.getY()).isEqualTo(y);
        }
    }
}

