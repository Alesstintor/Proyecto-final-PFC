package com.ales.fittrackapi.repositories;

import com.ales.fittrackapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
