package ir.maktabsharif.userprofile.service;

import ir.maktabsharif.userprofile.model.User;
import ir.maktabsharif.userprofile.model.dto.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void register(SignupRequestDto user) throws NoSuchAlgorithmException;
    Boolean isUsernameExist(SignupRequestDto requestDto);
    Boolean isEmailExist(SignupRequestDto requestDto);
    Optional<LoginResponseDto> login(LoginRequestDto requestDto);
    LoggedInDto loggedInUserByUsername(String username);
    Boolean updateUser(UpdateRequestDto updateRequestDto , String username);
    Boolean changePassword(String password , String username);
    List<User> allUsers();
}
