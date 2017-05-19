package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepository;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleServiceImpl<T extends Storable/*, R extends CrudRepository<T>*/> implements SimpleService<T> {

    @Autowired
    private CrudRepository<T> repo;

    private Class<T> clazz;

    public SimpleServiceImpl() {
        this(null);
    }

    public SimpleServiceImpl(Class<T> clazz) {
        this.repo = new CrudRepositoryImpl<>();
        this.clazz = clazz;
    }

    @Override
    public List<T> getALlEntities() {
        return repo.getAll(clazz);
    }

    @Override
    public List<T> findEntities(Criteria criteria) {
        return repo.find(criteria);
    }

    @Override
    public void saveEntities(List<T> entities) {
        entities.forEach((entity) -> {
            if (entity.getId() == 0) {
                repo.save(entity);
            } else {
                repo.update(entity);
            }
        });
    }
}
