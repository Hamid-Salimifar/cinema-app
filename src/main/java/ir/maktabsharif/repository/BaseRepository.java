package ir.maktabsharif.repository;

import java.util.List;

public interface BaseRepository<T> {
    T save(T entity);

    List<T> findAll();
    T findById(long id);

    T findByName(String name);

    T update(T entity);

    boolean deleteById(Integer id);
}
