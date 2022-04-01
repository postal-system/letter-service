package io.codero.letterservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RawLetterDto {
    private UUID id;
    private Instant timestamp;
    private Integer idReceiver;
    private Integer postOfficeReceiverId;
    private String content;
    private String sender;
}
