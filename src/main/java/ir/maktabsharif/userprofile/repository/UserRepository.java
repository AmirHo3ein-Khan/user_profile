package ir.maktabsharif.userprofile.repository;
import ir.maktabsharif.repository.BaseRepository;
import ir.maktabsharif.userprofile.model.User;
import ir.maktabsharif.userprofile.model.UserRole;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Boolean isUsernameExist(String username);
    Boolean isEmailExist(String email);
    UserRole findUserRoleByUserRoleName(String roleName);
}
