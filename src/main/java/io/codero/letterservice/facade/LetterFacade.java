package io.codero.letterservice.facade;

import io.codero.letterservice.dto.LetterDto;
import io.codero.letterservice.entity.Letter;
import io.codero.letterservice.mapper.LetterMapper;
import io.codero.letterservice.service.LetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class LetterFacade {
    private final LetterService letterService;
    private final LetterMapper letterMapper;

    public UUID save(LetterDto dto) {
        Letter letter = letterMapper.toEntity(dto);
        return letterService.save(letter);
    }

    public LetterDto getById(UUID id) {
        Letter letter = letterService.getById(id);
        return letterMapper.toDto(letter);
    }

    public List<LetterDto> getAll() {
        return letterService.getAll().stream()
                .map(letterMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LetterDto> getByListId(List<UUID> ids) {
        return letterService.getByListId(ids).stream()
                .map(letterMapper::toDto)
                .collect(Collectors.toList());
    }

    public void update(LetterDto dto) {
        Letter letter = letterMapper.toEntity(dto);
        letterService.update(letter);
    }

    public void delete(UUID id) {
        letterService.delete(id);
    }

    public void deleteAll() {
        letterService.deleteAll();
    }
}
