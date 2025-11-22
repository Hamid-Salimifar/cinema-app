package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.Rating;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.repository.impl.RatingRepositoryImpl;
import ir.maktabsharif.service.MovieService;
import ir.maktabsharif.service.RatingService;
import ir.maktabsharif.service.impl.MovieServiceImpl;
import ir.maktabsharif.service.impl.RatingServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.OptionalDouble;
import java.util.Set;

@WebServlet("/rate-movie")
public class RateMovieServlet extends HttpServlet {
    RatingService ratingService;
    MovieService movieService;

    @Override
    public void init() throws ServletException {
        this.movieService = new MovieServiceImpl(new MovieRepositoryImpl());
        this.ratingService = new RatingServiceImpl(new RatingRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userRatingStr = req.getParameter("userRating");
        String movieIdStr = req.getParameter("movieId");
        Integer userRating = Integer.parseInt(userRatingStr);
        Integer movieId = Integer.parseInt(movieIdStr);

        HttpSession session = req.getSession(false);
        Users user = (Users) session.getAttribute("user");

        Movie movie = movieService.findById(movieId);

        Rating existingRating = ratingService.findRatingByMovieAndUser(movie, user);
        if (existingRating != null) {
            existingRating.setRate(userRating);
            ratingService.update(existingRating);
        } else {
            Rating rating = new Rating(userRating);

            rating.setMovie(movie);
            rating.setUser(user);
            movie.getRatings().add(rating);
            ratingService.save(rating);
        }

        //Set<Rating> ratings = movie.getRatings();
        Set<Rating> ratings = movieService.findById(movieId).getRatings();
        Double average = ratings.stream().mapToInt(r -> r.getRate()).average().orElse(0.0);
        movie.setOverAllRating(average);

        movieService.update(movie);

        resp.sendRedirect(req.getContextPath() + "/movie-details?movieId=" + movieId);
    }
}
