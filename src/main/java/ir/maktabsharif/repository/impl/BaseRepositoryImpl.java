package ir.maktabsharif.repository.impl;

import ir.maktabsharif.repository.BaseRepository;
import ir.maktabsharif.util.JPAConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {
    private Class<T> entityClass;

    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public T save(T entity) {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        TypedQuery<T> query = em.createQuery("SELECT e from " + entityClass.getSimpleName() + " e", entityClass);
        List<T> list = query.getResultList();
        em.close();
        return list;
    }


    @Override
    public T findById(long id) {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        T entity = em.find(entityClass,id);
        em.close();
        return entity;
    }

    @Override
    public T findByName(String name) {
        EntityManager em=JPAConnection.getJPAInstance().getEntityManager();
        TypedQuery<T> query =em.createQuery("SELECT a from "+entityClass.getSimpleName()+" a where a.userName=:name",entityClass);
        query.setParameter("name",name);
        return query.getResultList().stream().findFirst().orElse(null);
    }
    @Override
    public T update(T entity) {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        em.getTransaction().begin();
        T updatedEntity = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return updatedEntity;
    }
    @Override
    public boolean deleteById(Integer id) {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        em.getTransaction().begin();
        T entity = em.find(entityClass,id);
        if(entity != null){
            em.remove(entity);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

}
