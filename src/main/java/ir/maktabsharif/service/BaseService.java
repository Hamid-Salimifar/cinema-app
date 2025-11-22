package ir.maktabsharif.service;

import java.util.List;

public interface BaseService<T> {
    T save(T entity);

    List<T> findAll();

    T findById(long id);

    T findByName(String name);

    T update(T entity);

    boolean deleteById(Integer id);

}
