package com.ales.fittrackapi.repositories;

import com.ales.fittrackapi.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
