package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.MovieService;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.service.impl.MovieServiceImpl;
import ir.maktabsharif.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/movie-library")
public class MovieLibraryServlet extends HttpServlet {
    private MovieService movieService;

    @Override
    public void init() throws ServletException {
        this.movieService=new MovieServiceImpl(new MovieRepositoryImpl());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movie> movies=movieService.findAll();
        req.setAttribute("movies",movies);
        req.getRequestDispatcher("/movie-library.jsp").forward(req,resp);
    }


}



