package by.siegell.soa.clinic.dao;

import by.siegell.soa.clinic.domain.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    void save(T entity);

    T findById(Long id);

    void delete(Long id);

    List<T> findAll();

    Optional<T> findBySubEntity(T entity);
}
