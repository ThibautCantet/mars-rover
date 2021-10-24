package com.mars.service;

import com.mars.model.*;
import com.mars.repository.JPAMarsRepository;
import com.mars.repository.JPARoverRepository;

import java.util.Optional;

public class RoverService {

    private final JPARoverRepository roverRepository;
    private final JPAMarsRepository marsRepository;
    private final Detector detector;

    public RoverService(JPARoverRepository roverRepository, JPAMarsRepository marsRepository, Detector detector) {
        this.roverRepository = roverRepository;
        this.marsRepository = marsRepository;
        this.detector = detector;
    }

    public void land(Coordinate coordinate, Direction direction) {
        final Rover rover = Rover.create(detector, coordinate, direction);
        roverRepository.save(rover);
    }

    public void command(Command command) {
        final Rover rover = roverRepository.find()
                .orElseThrow(NoLandedRoverException::new);
        rover.move(command);

        roverRepository.save(rover);

        final Mars mars = new Mars();
        mars.setX(rover.getX());
        mars.setY(rover.getY());
        mars.setAnObstacle(false);
        marsRepository.save(mars);
    }

    public Optional<Rover> getRover() {
        return roverRepository.find();
    }
}
