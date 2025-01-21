package ir.maktabsharif.userprofile.service;

import ir.maktabsharif.userprofile.exception.UserNotFoundException;
import ir.maktabsharif.userprofile.model.User;
import ir.maktabsharif.userprofile.model.dto.*;
import ir.maktabsharif.userprofile.repository.UserRepository;
import ir.maktabsharif.userprofile.repository.UserRepositoryImpl;
import ir.maktabsharif.userprofile.security.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public void register(SignupRequestDto requestDto) throws NoSuchAlgorithmException {
        String hashedPass = BCryptPasswordEncoder.encodeBcryptPassword(requestDto.getPassword());
        userRepository.save(User.builder()
                .username(requestDto.getUsername())
                .password(hashedPass)
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhone())
                .fullName(requestDto.getFullName())
                .userRole(userRepository.findUserRoleByUserRoleName("user"))
                .profileImage(requestDto.getProfileImage())
                .build());
    }

    @Override
    public Boolean isUsernameExist(SignupRequestDto requestDto) {
        return !userRepository.isUsernameExist(requestDto.getUsername());
    }

    @Override
    public Boolean isEmailExist(SignupRequestDto requestDto) {
        return !userRepository.isEmailExist(requestDto.getEmail());
    }

    @Override
    public Optional<LoginResponseDto> login(LoginRequestDto requestDto) {
        try {
            Optional<User> user = userRepository.findUserByUsername
                    (requestDto.getUsername());
            if (user.isPresent()) {
                if (BCryptPasswordEncoder.validateBcryptPassword(requestDto.getPassword(), user.get().getPassword())) {
                    return Optional.ofNullable(LoginResponseDto.builder()
                            .username(user.get().getUsername())
                            .password(user.get().getPassword())
                            .role(user.get().getUserRole())
                            .build());
                } else {
                    throw new UserNotFoundException("Invalid username or password!");
                }
            } else {
                throw new UserNotFoundException("User not found!");
            }
        } catch (UserNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public LoggedInDto loggedInUserByUsername(String username) {
        try {
            Optional<User> foundedUser = userRepository.findUserByUsername(username);
            return LoggedInDto.builder()
                    .username(foundedUser.get().getUsername())
                    .email(foundedUser.get().getEmail())
                    .fullName(foundedUser.get().getFullName())
                    .phoneNumber(foundedUser.get().getPhoneNumber())
                    .profileImage(foundedUser.get().getProfileImage())
                    .role(foundedUser.get().getUserRole())
                    .build();
        } catch (UserNotFoundException e) {
            return null;
        }
    }

    @Override
    public Boolean updateUser(UpdateRequestDto updateRequestDto , String username) {
        Optional<User> foundUser = userRepository.findUserByUsername(username);
        if (foundUser.isPresent()) {
            User user = User.builder()
                    .id(foundUser.get().getId())
                    .username(updateRequestDto.getUsername())
                    .password(foundUser.get().getPassword())
                    .email(updateRequestDto.getEmail())
                    .phoneNumber(updateRequestDto.getPhoneNumber())
                    .fullName(updateRequestDto.getFullName())
                    .profileImage(updateRequestDto.getProfileImage())
                    .userRole(foundUser.get().getUserRole())
                    .build();
            if (Optional.ofNullable(user).isPresent()) {
                userRepository.update(user);
                return true;
            } else return false;
        } else return false;
    }

    @Override
    public Boolean changePassword(String password, String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            String hashedPass = BCryptPasswordEncoder.encodeBcryptPassword(password);
            user.get().setPassword(hashedPass);
            userRepository.update(user.get());
            return true;
        } else return false;
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
