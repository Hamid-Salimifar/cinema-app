package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Users;
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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/watchlist")
public class WatchListServlet extends HttpServlet {
    private UserService userService;
    private MovieService movieService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserRepositoryImpl());
        this.movieService = new MovieServiceImpl(new MovieRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        Users user = (Users) session.getAttribute("user");
        Users freshUser = userService.findById(user.getId());
        session.setAttribute("user",freshUser);
        req.getRequestDispatcher("/WEB-INF/watchlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieIdStr = req.getParameter("movieId");
        Integer movieId = Integer.parseInt(movieIdStr);
        HttpSession session = req.getSession(false);
        Users user = (Users) session.getAttribute("user");
        Integer userID = user.getId();

        userService.addMovieToUser(userID, movieId);

        Users updatedUser=userService.findById(userID);
        session.setAttribute("user",updatedUser);

        resp.sendRedirect(req.getContextPath() + "/watchlist");


    }
}
