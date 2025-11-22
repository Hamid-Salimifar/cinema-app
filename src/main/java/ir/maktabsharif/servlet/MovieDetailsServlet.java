package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Comment;
import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.CommentRepositoryImpl;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.CommentService;
import ir.maktabsharif.service.MovieService;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.service.impl.CommentServiceImpl;
import ir.maktabsharif.service.impl.MovieServiceImpl;
import ir.maktabsharif.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/movie-details")
public class MovieDetailsServlet extends HttpServlet {

    MovieService movieService;
    CommentService commentService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        this.movieService = new MovieServiceImpl(new MovieRepositoryImpl());
        this.commentService = new CommentServiceImpl(new CommentRepositoryImpl());
        this.userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieIdStr = req.getParameter("movieId");
        Integer movieId = Integer.parseInt(movieIdStr);

        Movie movie = movieService.findById(movieId);
        req.setAttribute("movie", movie);
        req.getRequestDispatcher("WEB-INF/movie-details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieIdStr = req.getParameter("movieId");
        Integer movieId = Integer.parseInt(movieIdStr);
        String content = req.getParameter("content");

        HttpSession session = req.getSession(false);
        Users user = (Users) session.getAttribute("user");
        Movie movie = movieService.findById(movieId);

        Comment comment = Comment.builder()
                .authorName(user.getUserName())
                .content(content)
                .build();

        comment.setMovie(movie);
        comment.setUser(user);
        commentService.save(comment);

        resp.sendRedirect(req.getContextPath() + "/movie-details?movieId=" + movieId);


    }
}
