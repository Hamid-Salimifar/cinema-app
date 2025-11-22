package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.service.MovieService;
import ir.maktabsharif.service.impl.MovieServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchMovieServlet extends HttpServlet {
    MovieService movieService;

    @Override
    public void init() throws ServletException {
        this.movieService = new MovieServiceImpl(new MovieRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchedKeyword = req.getParameter("movie-name");
        List<Movie> movies=movieService.searchMovie(searchedKeyword);
        req.setAttribute("searchResults",movies);
        req.getRequestDispatcher("WEB-INF/search-result.jsp").forward(req,resp);
    }
}
