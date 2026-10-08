package ir.maktabsharif.userprofile.repository.base;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    void create(T entity);

    void update(T entity);

    Optional<T> find(Long id);

    void delete(Long id);

    List<T> findAll();

    Long getCount();
}
