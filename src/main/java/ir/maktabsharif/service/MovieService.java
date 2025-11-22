package ir.maktabsharif.service;

import ir.maktabsharif.model.Movie;

import java.util.List;

public interface MovieService extends BaseService<Movie>{

    void deleteMovieWithRelatedUsersById(Integer id);



    List<Movie> searchMovie(String keyword);
}
