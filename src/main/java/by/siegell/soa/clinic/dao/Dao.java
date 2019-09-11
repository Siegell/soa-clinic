package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.domain.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    Long create(T entity);

    T read(Long id);

    void update(T entity);

    void delete(Long id);

    List<T> readAll();
}
