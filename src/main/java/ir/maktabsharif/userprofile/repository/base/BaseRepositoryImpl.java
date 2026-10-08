package ir.maktabsharif.userprofile.repository.base;

import ir.maktabsharif.userprofile.model.BaseModel;
import ir.maktabsharif.userprofile.util.JpaUtil;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T extends BaseModel<Long>> implements BaseRepository<T> {
    private final Class<T> entityType;

    public BaseRepositoryImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public void create(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Optional<T> optional = this.find(entity.getId());
            if (optional.isPresent()) {
                T managedEntity = em.merge(entity);
                updateEntity(managedEntity);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<T> find(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        T entity = null;
        try {
            entity = em.find(entityType, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        Optional<T> t = find(id);
        if (t.isPresent()) {
            try {
                em.getTransaction().begin();
                T merge = em.merge(t.get());
                em.remove(merge);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = JpaUtil.getEntityManager().createQuery
                ("select s from " + getTableName() + " s ", entityType);
        return query.getResultList();
    }

    @Override
    public Long getCount() {
        return (long) this.findAll().size();
    }

    protected abstract String getTableName();
    protected abstract void updateEntity(T entity);
}
