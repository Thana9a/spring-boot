package com.example.autoconfiguration.Repository;

import com.example.autoconfiguration.Model.Clubs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Clubs, Long> {
    Optional<Clubs> findByTitle(String title);

}
