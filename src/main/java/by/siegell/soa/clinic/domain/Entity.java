package by.siegell.soa.clinic.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Entity {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
