package ir.maktabsharif.servlet.reserve;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.impl.MovieServiceImpl;
import ir.maktabsharif.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet("/user-movies")
public class UserMovieServlet extends HttpServlet {
    private UserServiceImpl userService;
    private MovieServiceImpl movieService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserRepositoryImpl());
        this.movieService = new MovieServiceImpl(new MovieRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String strUserId = req.getParameter("user-id");
        if (strUserId == null || strUserId.isEmpty()) {
            return;
        }
        Integer userID = Integer.parseInt(strUserId);
        Users user = userService.findById(userID);

        if (user == null) {
            out.println("<h3>User not found!</h3>");
            return;
        }


        Set<Movie> movies = user.getMovies();
        out.println("<h1>this is your movies</h1>");
        for (Movie movie : movies) {
            out.println("<h3>" + movie.getTitle() + "</h3><br>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String userIdStr = req.getParameter("userId");
        String movieIdStr = req.getParameter("movieId");
        Integer userId = null;
        Integer movieId = null;
        if (userIdStr != null && !userIdStr.isEmpty()) {
            userId = Integer.parseInt(userIdStr);
        }
        if (movieIdStr != null && !movieIdStr.isEmpty()) {
            movieId = Integer.parseInt(movieIdStr);
        }

        userService.addMovieToUser(userId, movieId);

        PrintWriter out = resp.getWriter();
        out.println("<h1>Movie added to your watchlist</h1>");

    }
}
