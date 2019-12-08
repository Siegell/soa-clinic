package by.siegell.soa.clinic.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    void save(T entity);

    T findById(Long id);

    void delete(Long id);

    List<T> findAll();

    Optional<T> findBySubEntity(T entity);
}
