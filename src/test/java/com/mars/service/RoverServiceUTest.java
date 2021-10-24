package com.mars.service;

import com.mars.model.*;
import com.mars.repository.JPAMarsRepository;
import com.mars.repository.JPARoverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;


class RoverServiceUTest {

    private RoverService roverService;

    private final JPAMarsRepository marsRepository = mock(JPAMarsRepository.class);
    private final JPARoverRepository roverRepository = mock(JPARoverRepository.class);
    private final Detector detector = mock(Detector.class);

    @BeforeEach
    void setUp() {
        roverService = new RoverService(roverRepository, marsRepository, detector);
    }

    @Test
    void land_rover_save_landed_mars_rover() {
        // when
        roverService.land(new Coordinate(0, 0), Direction.W);

        // then
        ArgumentCaptor<Rover> roverArgumentCaptor = ArgumentCaptor.forClass(Rover.class);
        verify(roverRepository).save(roverArgumentCaptor.capture());
        assertThat(roverArgumentCaptor.getValue().getId()).isInstanceOf(UUID.class);
        assertThat(roverArgumentCaptor.getValue().getX()).isZero();
        assertThat(roverArgumentCaptor.getValue().getY()).isZero();
        assertThat(roverArgumentCaptor.getValue().getDirection()).isEqualTo(Direction.W);
    }

    @Nested
    class GetRoverShould {

        @Test
        void return_empty_when_no_landed_rover() {
            // when
            final Optional<Rover> rover = roverService.getRover();

            // then
            assertThat(rover).isEmpty();
        }

        @Test
        void return_landed_rover() {
            // given
            final Rover rover = mock(Rover.class);
            when(roverRepository.find()).thenReturn(Optional.ofNullable(rover));

            // when
            final Optional<Rover> result = roverService.getRover();

            // then
            assertThat(result).contains(rover);
        }
    }

    @Nested
    class CommandShould {

        @Test
        void throw_exception_when_no_landed_rover() {
            // when
            final Throwable throwable = catchThrowable(() -> roverService.command(Command.FORWARD));

            // then
            assertThat(throwable).isInstanceOf(NoLandedRoverException.class);
        }

        @Test
        void command_should_update_rover_when_moving_forward() {
            // given
            final UUID roverId = UUID.randomUUID();
            when(roverRepository.find()).thenReturn(Optional.of(Rover.of(roverId, detector, new Coordinate(0, 0), Direction.S)));

            // when
            roverService.command(Command.FORWARD);

            // then
            verify(roverRepository).save(Rover.of(roverId, detector, new Coordinate(0, 4), Direction.S));
        }

        @Test
        void command_should_save_new_entry_in_mars_when_moving_forward() {
            // given
            final UUID roverId = UUID.randomUUID();
            when(roverRepository.find()).thenReturn(Optional.of(Rover.of(roverId, detector, new Coordinate(0, 0), Direction.S)));

            // when
            roverService.command(Command.FORWARD);

            // then
            ArgumentCaptor<Mars> marsArgumentCaptor = ArgumentCaptor.forClass(Mars.class);
            verify(marsRepository).save(marsArgumentCaptor.capture());
            assertThat(marsArgumentCaptor.getValue().getX()).isZero();
            assertThat(marsArgumentCaptor.getValue().getY()).isEqualTo(4);
            assertThat(marsArgumentCaptor.getValue().isAnObstacle()).isFalse();

        }
    }
}