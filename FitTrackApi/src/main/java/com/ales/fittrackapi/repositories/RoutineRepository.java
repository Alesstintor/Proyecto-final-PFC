package com.ales.fittrackapi.repositories;

import com.ales.fittrackapi.entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
