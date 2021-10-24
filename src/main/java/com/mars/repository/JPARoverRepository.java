package com.mars.repository;

import com.mars.model.Rover;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JPARoverRepository extends CrudRepository<Rover, UUID> {
    default Optional<Rover> find() {
        return Optional.ofNullable(findAll().iterator().next());
    }
}
