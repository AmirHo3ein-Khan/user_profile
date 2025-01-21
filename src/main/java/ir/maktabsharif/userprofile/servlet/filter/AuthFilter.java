package ir.maktabsharif.userprofile.servlet.filter;

import ir.maktabsharif.userprofile.exception.MustLoginException;
import ir.maktabsharif.userprofile.exception.UsernameOrPasswordIncorrectException;
import ir.maktabsharif.userprofile.model.dto.LoginRequestDto;
import ir.maktabsharif.userprofile.model.dto.LoginResponseDto;
import ir.maktabsharif.userprofile.security.BCryptPasswordEncoder;
import ir.maktabsharif.userprofile.service.UserService;
import ir.maktabsharif.userprofile.service.UserServiceImpl;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class AuthFilter implements Filter {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            if (Optional.ofNullable(username).isPresent() && Optional.ofNullable(password).isPresent()) {

                LoginRequestDto loginRequestDto =
                        LoginRequestDto.builder()
                                .username(username)
                                .password(password)
                                .build();

                Optional<LoginResponseDto> login = userService.login(loginRequestDto);


                if (login.isPresent()) {
                    if (username.equals(login.get().getUsername()) &&
                            BCryptPasswordEncoder.validateBcryptPassword(password, login.get().getPassword())) {
                        chain.doFilter(request, response);
                    } else {
                        throw new UsernameOrPasswordIncorrectException("Username or password is wrong");
                    }
                }
            } else {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                String servletPath = httpServletRequest.getServletPath();
                session.setAttribute("path" , servletPath);
                throw new MustLoginException("You must login to see the content");
            }
        } catch (Exception e) {
            ((HttpServletResponse) response).sendRedirect("login.jsp");

        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
