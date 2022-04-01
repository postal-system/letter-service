package io.codero.letterservice.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.codero.letterservice.dto.RawLetterDto;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Letter {
    @Id
    @Column(name = "id", columnDefinition = "pg-uuid")
    private UUID id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

    @Column(name = "post_office_id", nullable = false)
    private Integer postOfficeId;

    @Column(name = "time_stamp")
    private Instant timestamp;

    @Column
    private String content;

    @Type(type = "jsonb")
    @Column(name = "raw_letter", nullable = false, columnDefinition = "jsonb")
    private RawLetterDto rawLetterDto;
}
