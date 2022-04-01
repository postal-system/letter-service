package io.codero.letterservice.repository;

import io.codero.letterservice.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface LetterRepository extends JpaRepository<Letter, UUID> {
}
