package io.codero.letterservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class LetterDto {
    private UUID id;
    private String sender;
    private String content;
    private String receiver;
    private Instant timestamp;
    private Integer postOfficeId;
    private RawLetterDto rawLetterDto;
}
