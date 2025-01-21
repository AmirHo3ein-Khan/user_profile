package ir.maktabsharif.userprofile.repository;

import ir.maktabsharif.repository.BaseRepositoryImpl;
import ir.maktabsharif.userprofile.exception.UserNotFoundException;
import ir.maktabsharif.userprofile.model.User;
import ir.maktabsharif.userprofile.model.UserRole;
import ir.maktabsharif.userprofile.model.queryresult.ExistEmail;
import ir.maktabsharif.userprofile.model.queryresult.ExistUsername;
import ir.maktabsharif.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {
    @Override
    protected Class<User> getClassName() {
        return User.class;
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
}
