package ir.maktabsharif.repository;

import ir.maktabsharif.model.Movie;

import java.util.List;

public interface MovieRepository extends BaseRepository<Movie>{

    List<Movie> searchMovie(String keyword);
}
