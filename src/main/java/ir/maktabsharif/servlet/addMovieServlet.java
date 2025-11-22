package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Movie;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.MovieRepositoryImpl;
import ir.maktabsharif.service.impl.MovieServiceImpl;
import ir.maktabsharif.util.ImageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/add-movies")
@MultipartConfig
public class addMovieServlet extends HttpServlet {
    private MovieServiceImpl movieService;

    @Override
    public void init() throws ServletException {
        this.movieService = new MovieServiceImpl(new MovieRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        HttpSession session=req.getSession(false);
        Users user = (Users)session.getAttribute("user");
        if(user.getRole().toString().equals("ADMIN")){
            req.getRequestDispatcher("/WEB-INF/add-movies.jsp").forward(req, resp);
        }else{
            resp.setContentType("text/html");
            PrintWriter out=resp.getWriter();
            out.println("<h1>you dont have access only admin can add movies</h1>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String releaseDate = req.getParameter("releaseDate");
        String genre = req.getParameter("genre");
        String duration = req.getParameter("duration");

        String ratingStr = req.getParameter("rating");
        Double overAllRating=Double.parseDouble(ratingStr);

        Part poster= req.getPart("poster");
        String encodedPoster = ImageUtil.encodeImage(poster.getInputStream(), poster.getContentType());


        Movie movie = Movie.builder()
                .title(title)
                .description(description)
                .releaseDate(releaseDate)
                .genre(genre)
                .duration(duration)
                .overAllRating(overAllRating)
                .posterImage(encodedPoster)
                .build();


        movieService.save(movie);
        PrintWriter out = resp.getWriter();
        resp.sendRedirect(req.getContextPath()+"/movie-library");
    }
}
