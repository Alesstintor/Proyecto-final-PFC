package com.ales.fittrackapi.repositories;

import com.ales.fittrackapi.entities.Record;
import com.ales.fittrackapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByUser(User user);
}
