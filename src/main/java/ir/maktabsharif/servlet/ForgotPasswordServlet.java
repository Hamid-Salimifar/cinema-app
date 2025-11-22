package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService=new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/forgot-password-validation.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName=req.getParameter("username");
        String email=req.getParameter("email");
        Users user = userService.findByName(userName);
        if(user.getEmail().equalsIgnoreCase(email)){
            resp.sendRedirect(req.getContextPath() + "/change-password");
        }else {
            resp.setContentType("text/html");
            PrintWriter out=resp.getWriter();
            out.println("<h1>the email is not valid!!!</h1>");
        }
    }
}
