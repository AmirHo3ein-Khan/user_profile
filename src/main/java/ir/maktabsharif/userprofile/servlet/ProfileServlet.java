package ir.maktabsharif.userprofile.servlet;
import ir.maktabsharif.userprofile.model.dto.LoggedInDto;
import ir.maktabsharif.userprofile.service.UserService;
import ir.maktabsharif.userprofile.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        LoggedInDto user = userService.loggedInUserByUsername(username);
        req.setAttribute("user", user);
        req.getRequestDispatcher("profile.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        LoggedInDto user = userService.loggedInUserByUsername(username);
        req.setAttribute("user", user);
        req.getRequestDispatcher("profile.jsp").forward(req,resp);
    }
}
