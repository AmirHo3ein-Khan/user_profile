package ir.maktabsharif.userprofile.repository;
import ir.maktabsharif.userprofile.model.User;
import ir.maktabsharif.userprofile.model.UserRole;
import ir.maktabsharif.userprofile.repository.base.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findUserByUsername(String username);
    Boolean isUsernameExist(String username);
    Boolean isEmailExist(String email);
    UserRole findUserRoleByUserRoleName(String roleName);
}
