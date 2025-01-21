package ir.maktabsharif.userprofile.servlet;
import ir.maktabsharif.userprofile.model.dto.SignupRequestDto;
import ir.maktabsharif.userprofile.security.Base64ImageEncoder;
import ir.maktabsharif.userprofile.service.UserService;
import ir.maktabsharif.userprofile.service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@MultipartConfig
public class SignupServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullName = req.getParameter("fullName");
        String phoneNumber = req.getParameter("phoneNumber");

        Part filePart = req.getPart("profileImage");

        InputStream fileContent = filePart.getInputStream();

        String image = Base64ImageEncoder.imageEncoder(fileContent);

        SignupRequestDto signupRequestDto = SignupRequestDto.builder()
                .username(username)
                .password(password)
                .email(email)
                .fullName(fullName)
                .phone(phoneNumber)
                .profileImage(image)
                .build();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<SignupRequestDto>> validate = validator.validate(signupRequestDto);

        if (!validate.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<SignupRequestDto> constraintViolation : validate) {
                errors.append(constraintViolation.getMessage()).append("<br>");
            }
            req.setAttribute("massage", errors);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            if (userService.isUsernameExist(signupRequestDto)) {
                if (userService.isEmailExist(signupRequestDto)) {
                    try {
                        userService.register(signupRequestDto);
                        req.getSession().setAttribute("massage", "Signup successfully");
                        resp.sendRedirect("login.jsp");
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    req.setAttribute("massage", "This email already exist please try something else!");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("massage", "This username already exist please try something else!");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
    }
}
