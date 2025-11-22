package ir.maktabsharif.service;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.Rating;
import ir.maktabsharif.model.Users;

public interface RatingService extends BaseService<Rating>{


    Rating findRatingByMovieAndUser(Movie movie, Users user);

}
