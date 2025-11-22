package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.repository.MovieRepository;
import ir.maktabsharif.util.JPAConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MovieRepositoryImpl extends BaseRepositoryImpl<Movie> implements MovieRepository {
    public MovieRepositoryImpl() {
        super(Movie.class);

    }

    @Override
    public List<Movie> searchMovie(String keyword) {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        String jpql = "SELECT m from Movie m where lower(m.title) like lower(concat('%', :keyword, '%')) " +
                "or lower(m.genre) like lower(concat('%', :keyword, '%'))";
        TypedQuery<Movie> query = em.createQuery(jpql, Movie.class);
        query.setParameter("keyword", keyword);
        return query.getResultList();
    }
}
