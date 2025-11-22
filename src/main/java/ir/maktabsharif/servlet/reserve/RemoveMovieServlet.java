package ir.maktabsharif.servlet.reserve;

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

@WebServlet("/remove")
public class RemoveMovieServlet extends HttpServlet {
    private UserServiceImpl userService;
    private MovieServiceImpl movieService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserRepositoryImpl());
        this.movieService = new MovieServiceImpl(new MovieRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdStr = req.getParameter("userId");
        String movieIdStr = req.getParameter("movieId");

        Integer userID = Integer.parseInt(userIdStr);
        Integer movieID = Integer.parseInt(movieIdStr);

        userService.removeMovieFromUserWatchlist(userID, movieID);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>the movie has been removed from your watchlist</h1>");
    }
}
