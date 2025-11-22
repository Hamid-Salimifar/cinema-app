package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.service.impl.UserServiceImpl;
import ir.maktabsharif.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService=new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Users user = userService.loginUser(username, password);
        if(user==null){
            resp.sendRedirect(req.getContextPath()+"/login.html");
            return;
        }
        HttpSession oldSession=req.getSession(false);
        if(oldSession!=null){
            oldSession.invalidate();
        }
        HttpSession session= req.getSession(true);
        session.setMaxInactiveInterval(30*60);
        session.setAttribute("username",username);
        session.setAttribute("user",user);
        session.setAttribute("role",user.getRole().toString());
        resp.sendRedirect(req.getContextPath()+"/movie-library");

    }

}
