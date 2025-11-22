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

import java.io.IOException;


@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService=new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/change-pass.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPass=req.getParameter("password");
        String userName=req.getParameter("username");
        String hashPass= PasswordUtil.hashPassword(newPass);
        Users user=userService.findByName(userName);
        user.setPassword(hashPass);
        userService.update(user);
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }
}
