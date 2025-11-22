package ir.maktabsharif.service.impl;

import ir.maktabsharif.exception.MovieNotFound;
import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.MovieRepository;
import ir.maktabsharif.repository.impl.BaseRepositoryImpl;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.service.MovieService;
import ir.maktabsharif.util.JPAConnection;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {
    MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepositoryImpl movieRepositoryRepositoryImpl) {
        super(movieRepositoryRepositoryImpl);
        this.movieRepository = new MovieRepositoryImpl();
    }

    @Override
    public List<Movie> searchMovie(String keyword) {
        return movieRepository.searchMovie(keyword);
    }


    @Override
    public void deleteMovieWithRelatedUsersById(Integer id) {
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        em.getTransaction().begin();
        Movie movie = em.find(Movie.class, id);
        movie.getUsers().forEach(u -> u.getMovies().remove(movie));
        em.remove(movie);
        em.getTransaction().commit();
        em.close();
    }
}
