package ir.maktabsharif.service.impl;

import ir.maktabsharif.repository.BaseRepository;
import ir.maktabsharif.repository.impl.BaseRepositoryImpl;
import ir.maktabsharif.service.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
   protected BaseRepositoryImpl<T> baseRepositoryImpl;

    public BaseServiceImpl(BaseRepositoryImpl<T> baseRepositoryImpl) {
        this.baseRepositoryImpl = baseRepositoryImpl;
    }

    @Override
    public T save(T entity) {
        return baseRepositoryImpl.save(entity);
    }
    @Override
    public List<T> findAll() {
        return baseRepositoryImpl.findAll();
    }

    @Override
    public T findById(long id) {
        return baseRepositoryImpl.findById(id);
    }

    @Override
    public T findByName(String name) {
        return baseRepositoryImpl.findByName(name);
    }

    @Override
    public T update(T entity) {
        return baseRepositoryImpl.update(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return baseRepositoryImpl.deleteById(id);
    }
}
