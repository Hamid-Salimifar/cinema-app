package ir.maktabsharif.servlet;

import ir.maktabsharif.model.UserRole;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.impl.UserServiceImpl;
import ir.maktabsharif.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/users")
public class UserRegistrationServlet extends HttpServlet {
    private UserServiceImpl userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/users_registration.html").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("uname");
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        String hashPass= PasswordUtil.hashPassword(password);

        if(userService.findByName(username) !=null){
            out.println("<h1>username already taken!!!  please try other names...</h1>");
            return;
        }

        Users user = Users.builder()
                .email(email)
                .userName(username)
                .password(hashPass)
                .build();



        Users save = userService.save(user);

        if(save!=null){
            out.println("<h1>User Registered Successfully</h1>");
        }else{
            out.println("<h1>User Registration failed</h1>");
        }
    }


}
