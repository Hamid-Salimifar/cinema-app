package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.service.MovieService;
import ir.maktabsharif.service.impl.MovieServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-movie")
public class DeleteServlet extends HttpServlet {
    MovieService movieService;

    @Override
    public void init() throws ServletException {
        this.movieService=new MovieServiceImpl(new MovieRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieIdStr=req.getParameter("id");
        Integer movieId = Integer.parseInt(movieIdStr);

        movieService.deleteMovieWithRelatedUsersById(movieId);

        resp.sendRedirect(req.getContextPath()+"/movie-library");
    }
}
