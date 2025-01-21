package ir.maktabsharif.userprofile.servlet;

import ir.maktabsharif.userprofile.model.dto.LoginRequestDto;
import ir.maktabsharif.userprofile.model.dto.LoginResponseDto;
import ir.maktabsharif.userprofile.service.UserService;
import ir.maktabsharif.userprofile.service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (Optional.ofNullable(username).isPresent() && Optional.ofNullable(password).isPresent()) {

            LoginRequestDto loginRequestDto =
                    LoginRequestDto.builder()
                            .username(username)
                            .password(password)
                            .build();

            Optional<LoginResponseDto> login = userService.login(loginRequestDto);

            if (login.isPresent()) {
                HttpSession session = req.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("password", password);

                String path = (String)session.getAttribute("path");

                if (path != null){
                    req.getRequestDispatcher(path).forward(req,resp);
                } else {
                    req.getRequestDispatcher("profile").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Username or Password is incorrect!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Username or Password is incorrect!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
