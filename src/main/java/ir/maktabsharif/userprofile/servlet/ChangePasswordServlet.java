package ir.maktabsharif.userprofile.servlet;
import ir.maktabsharif.userprofile.service.UserService;
import ir.maktabsharif.userprofile.service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = (String) req.getSession().getAttribute("username");
        Boolean isPasswordChange = userService.changePassword(password, username);
        if (isPasswordChange) {
            req.getSession().setAttribute("massage", "password change successfully");
            resp.sendRedirect("login.jsp");
        } else {
            req.setAttribute("error", "password not change something went wrong!");
            resp.sendRedirect("login.jsp");
        }
    }
}
