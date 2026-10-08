package ir.maktabsharif.userprofile.servlet;
import ir.maktabsharif.userprofile.model.User;
import ir.maktabsharif.userprofile.service.UserService;
import ir.maktabsharif.userprofile.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllUserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.allUsers();
        req.getSession().setAttribute("users" , users);
        resp.sendRedirect("seeAllUser.jsp");
    }
}
