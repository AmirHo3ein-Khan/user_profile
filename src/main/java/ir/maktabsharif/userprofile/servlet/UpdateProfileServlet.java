package ir.maktabsharif.userprofile.servlet;

import ir.maktabsharif.userprofile.model.dto.LoggedInDto;
import ir.maktabsharif.userprofile.model.dto.SignupRequestDto;
import ir.maktabsharif.userprofile.model.dto.UpdateRequestDto;
import ir.maktabsharif.userprofile.security.Base64ImageEncoder;
import ir.maktabsharif.userprofile.service.UserService;
import ir.maktabsharif.userprofile.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");

        Part filePart = request.getPart("profileImage");
        InputStream fileContent = filePart.getInputStream();

        String image = Base64ImageEncoder.imageEncoder(fileContent);

        UpdateRequestDto user = UpdateRequestDto.builder()
                .username(username)
                .email(email)
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .profileImage(image)
                .build();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<UpdateRequestDto>> validate = validator.validate(user);

        if (validate.isEmpty()) {

            String usernameForUpdate = (String) request.getSession().getAttribute("username");

            Boolean isUpdated = userService.updateUser(user, usernameForUpdate);
            if (isUpdated) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                LoggedInDto loggedInDto = userService.loggedInUserByUsername(username);
                request.setAttribute("user", loggedInDto);
                request.getSession().setAttribute("massage", "Update successfully");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("massage", "Not update!");
            }
        } else {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<UpdateRequestDto> constraintViolation : validate) {
                errors.append(constraintViolation.getMessage()).append("<br>");
            }
            request.getSession().setAttribute("error", errors);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }
}
