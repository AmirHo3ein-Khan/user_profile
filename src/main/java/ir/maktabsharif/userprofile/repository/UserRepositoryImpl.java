package ir.maktabsharif.userprofile.repository;

import ir.maktabsharif.userprofile.exception.UserNotFoundException;
import ir.maktabsharif.userprofile.model.User;
import ir.maktabsharif.userprofile.model.UserRole;
import ir.maktabsharif.userprofile.model.queryresult.ExistEmail;
import ir.maktabsharif.userprofile.model.queryresult.ExistUsername;
import ir.maktabsharif.userprofile.repository.base.BaseRepositoryImpl;
import ir.maktabsharif.userprofile.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }


    @Override
    public Optional<User> findUserByUsername(String username) {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            return Optional.ofNullable(em.createQuery("from User u where u.username =: username", User.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (NoResultException e) {
            throw  new UserNotFoundException("User not found!");
        }
    }

    @Override
    public Boolean isUsernameExist(String username) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        return entityManager.createQuery(
                        "select new ir.maktabsharif.userprofile.model.queryresult.ExistUsername ( " +
                                "case when count (ua)<>0 then true " +
                                "else false " +
                                "end " +
                                ") From User ua where ua.username =: username ", ExistUsername.class)
                .setParameter("username", username)
                .getSingleResult().getIsExist();
    }

    @Override
    public Boolean isEmailExist(String email) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        return entityManager.createQuery(
                        "select new ir.maktabsharif.userprofile.model.queryresult.ExistEmail ( " +
                                "case when count (ua)<>0 then true " +
                                "else false " +
                                "end " +
                                ") From User ua where ua.email =: email ", ExistEmail.class)
                .setParameter("email", email)
                .getSingleResult().getIsExist();
    }

    @Override
    public UserRole findUserRoleByUserRoleName(String roleName) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("from UserRole ur where ur.role =: roleName ", UserRole.class)
                .setParameter("roleName", roleName)
                .getResultList().get(0);
    }

    @Override
    protected String getTableName() {
        return "User";
    }

    @Override
    protected void updateEntity(User entity) {

    }
}
