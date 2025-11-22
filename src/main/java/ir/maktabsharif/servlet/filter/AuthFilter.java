package ir.maktabsharif.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        String path = request.getRequestURI().substring(request.getContextPath().length());

        boolean isPublic = path.equals("/login.html") ||
                path.equals("/login") ||
                path.equals("/index.jsp") ||
                path.equals("/users") ||
                path.equals("/forgot-password") ||
                path.equals("/change-password") ||
                path.equals("/");

        boolean loggedIn=(session != null && session.getAttribute("user") !=null);
        if (loggedIn || isPublic){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/login.html"); //zereshk
        }
    }
}
