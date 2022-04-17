package io.codero.letterservice.service.impl;

import io.codero.letterservice.entity.Letter;
import io.codero.letterservice.exception.IdAlreadyExistException;
import io.codero.letterservice.exception.NotFoundException;
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
    public synchronized UUID save(Letter letter) {
        if (repository.existsById(letter.getId())) {
            throw new IdAlreadyExistException(String.format("Letter with ID: %s already exist, ", letter.getId()));
        }
        log.info("save {} to DB", letter);
        return repository.save(letter).getId();
    }

    @Override
    public Letter getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Letter with ID: %s not found " + id));
    }

    @Override
    public List<Letter> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Letter> getByListId(List<UUID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void update(Letter letter) {
        if (!repository.existsById(letter.getId())) {
            throw new NotFoundException(String.format("Letter with ID: %s cannot be update", letter.getId()));
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
