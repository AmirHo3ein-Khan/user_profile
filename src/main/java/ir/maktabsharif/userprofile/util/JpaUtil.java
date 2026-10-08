package ir.maktabsharif.userprofile.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory entityManagerFactory = null;
    private JpaUtil(){}

    private static EntityManagerFactory getEntityManagerFactory(){
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("postgres");
        }
        return entityManagerFactory;
    }
    public static EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }
}
