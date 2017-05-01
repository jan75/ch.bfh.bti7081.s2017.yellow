package ch.bfh.bti7081.s2017.yellow.repositories;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import org.hibernate.Criteria;

import java.util.ArrayList;
import java.util.List;

public class CrudRepositoryImpl<T extends Storable> implements CrudRepository<T> {

    @Override
    public List<T> getAll(Class<T> clazz) {
        return new ArrayList<>();
    }

    @Override
    public List<T> find(Criteria criteria) {
        return null;
    }

    @Override
    public void save(T entity) {}

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }
}
