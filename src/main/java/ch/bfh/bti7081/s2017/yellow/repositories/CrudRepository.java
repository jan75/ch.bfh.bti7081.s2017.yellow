package ch.bfh.bti7081.s2017.yellow.repositories;


import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import org.hibernate.Criteria;

import java.util.List;

public interface CrudRepository<T extends Storable> {

        List<T> getAll(Class<T> clazz);

        List<T> find(Criteria criteria);

        void save(T entity);

        void update(T entity);

        void delete(T entity);
}
