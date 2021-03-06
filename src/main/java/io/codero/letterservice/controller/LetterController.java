package io.codero.letterservice.controller;


import io.codero.letterservice.dto.LetterDto;
import io.codero.letterservice.facade.LetterFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/letters")
public class LetterController {
    private final LetterFacade facade;

    @PostMapping
    public UUID save(@RequestBody LetterDto dto) {
        return facade.save(dto);
    }

    @PostMapping("/ids")
    public List<LetterDto> getByListId(@RequestBody List<UUID> ids) {
        return facade.getByListId(ids);
    }

    @GetMapping("/{id}")
    public LetterDto getById(@PathVariable("id") UUID id) {
        return facade.getById(id);
    }

    @GetMapping
    public List<LetterDto> getAll() {
        return facade.getAll();
    }

    @PutMapping
    public void update(@RequestBody final LetterDto dto) {
        facade.update(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        facade.delete(id);
    }

    @DeleteMapping
    public void deleteAll() {
        facade.deleteAll();
    }
}
