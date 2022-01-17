package io.codero.letterservice.mapper;

import io.codero.letterservice.dto.LetterDto;
import io.codero.letterservice.entity.Letter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface LetterMapper {
    LetterDto toDto(Letter letter);

    Letter toEntity(LetterDto dto);
}
