package ir.maktabsharif.userprofile.listener;

import ir.maktabsharif.userprofile.model.Permission;
import ir.maktabsharif.userprofile.model.User;
import ir.maktabsharif.userprofile.model.UserRole;
import ir.maktabsharif.userprofile.security.BCryptPasswordEncoder;
import ir.maktabsharif.userprofile.util.ReadXml;
import ir.maktabsharif.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@WebListener
public class UserProfileServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        securityDataInitializer();
        adminInitializer();
    }

    private void adminInitializer() {
        EntityManager entityManager = JpaUtil.getEntityManager();

        User existingAdmin = entityManager.createQuery(
                        "select u from User u where u.userRole.role = :role", User.class)
                .setParameter("role", "admin")
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        if (existingAdmin == null) {
            UserRole role = entityManager.createQuery(
                            "select r from UserRole r where r.role = :role", UserRole.class)
                    .setParameter("role", "admin")
                    .getSingleResult();

            File inputImage = new File("D:\\UserProfile\\src\\main\\resources\\AdminPicture.png");
            byte [] encodeImage;
            try {
                encodeImage = Base64.getEncoder().encode(Files.readAllBytes(inputImage.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String encoded = new String(encodeImage);

            String password = BCryptPasswordEncoder.encodeBcryptPassword("admin");
            User user = User.builder()
                    .username("admin")
                    .password(password)
                    .fullName("Amir Hossein")
                    .userRole(role)
                    .email("admin@gmail.com")
                    .phoneNumber("+989913094528")
                    .profileImage(encoded)
                    .build();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        }
    }

    private void securityDataInitializer() {
        ReadXml.readXml();

        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        for (Permission permissionItem : ReadXml.getPermissionList()) {
            try {
                Permission existingPermission = entityManager.createQuery(
                                "SELECT p FROM Permission p WHERE p.permission = :permission", Permission.class)
                        .setParameter("permission", permissionItem.getPermission()).getSingleResult();
                if (existingPermission != null) {
                    return;
                }
            } catch (Exception e) {
                entityManager.persist(permissionItem);
            }
        }

        for (UserRole roleItem : ReadXml.getUserRoleList()) {
            try {
                UserRole existingRole = entityManager.createQuery(
                                "SELECT u FROM UserRole u WHERE u.role = :role", UserRole.class)
                        .setParameter("role", roleItem.getRole()).getSingleResult();
                if (existingRole != null) {
                    return;
                }

            } catch (Exception e) {
                entityManager.persist(roleItem);

            }
        }

        transaction.commit();
    }
}
