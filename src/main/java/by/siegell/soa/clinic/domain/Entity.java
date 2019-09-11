package by.siegell.soa.clinic.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public abstract class Entity {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
