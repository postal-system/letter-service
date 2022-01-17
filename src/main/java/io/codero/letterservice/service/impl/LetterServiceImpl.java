package io.codero.letterservice.service.impl;

import io.codero.letterservice.entity.Letter;
import io.codero.letterservice.exception.CastIdAlreadyExistException;
import io.codero.letterservice.exception.CastNotFoundException;
import io.codero.letterservice.repository.LetterRepository;
import io.codero.letterservice.service.LetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LetterServiceImpl implements LetterService {
    private final LetterRepository repository;

    @Override
    public UUID save(Letter letter) {
        if (repository.existsById(letter.getId())) {
            throw new CastIdAlreadyExistException(String.format("Object with ID: %s already exist, ", letter.getId()));
        }
        return repository.save(letter).getId();
    }

    @Override
    public Letter getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new CastNotFoundException("Not found by ID: " + id));
    }

    @Override
    public List<Letter> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Letter letter) {
        if (!repository.existsById(letter.getId())) {
            throw new CastNotFoundException(String.format("Object with ID: %s cannot by update", letter.getId()));
        }
        repository.save(letter);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
