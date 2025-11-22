package ir.maktabsharif.servlet;

import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.service.impl.UserServiceImpl;
import ir.maktabsharif.util.ImageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/editInformation")
@MultipartConfig
public class EditInformationServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserRepositoryImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        HttpSession session = req.getSession(false);
        Users user = (Users) session.getAttribute("user");

        if (user != null) {
            user.setUserName(username);
            user.setEmail(email);
            Part image = req.getPart("image");
            if (image != null && image.getSize() > 0) {
                String encodedImage = ImageUtil.encodeImage(image.getInputStream(), image.getContentType());
                user.setProfileImage(encodedImage);
            }
        }

        Users updatedUser=userService.update(user);
        session.setAttribute("user",updatedUser);
//        Users user2 = (Users) session.getAttribute("user");
//        System.out.println("Session user image length: " + (user2.getProfileImage() != null ? user2.getProfileImage().length() : 0));
//        Users userFromDb = userService.findById(user2.getId());
//        System.out.println("DB user image length: " + (userFromDb.getProfileImage() != null ? userFromDb.getProfileImage().length() : 0));

        resp.sendRedirect(req.getContextPath() + "/edit-information.jsp");


    }
}
