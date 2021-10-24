package com.mars.repository;

import com.mars.model.Mars;
import com.mars.model.Rover;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface JPAMarsRepository extends CrudRepository<Mars, UUID> {
    Optional<Rover> find();
}
