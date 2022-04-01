package io.codero.letterservice.service;

import io.codero.letterservice.entity.Letter;

import java.util.List;
import java.util.UUID;

public interface LetterService {
    UUID save(Letter letter);

    Letter getById(UUID id);

    void update(Letter letter);

    void delete(UUID id);

    void deleteAll();

    List<Letter> getAll();

    List<Letter> getByListId(List<UUID> ids);

}
