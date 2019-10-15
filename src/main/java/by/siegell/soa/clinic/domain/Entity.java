package by.siegell.soa.clinic.domain;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public abstract class Entity {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
