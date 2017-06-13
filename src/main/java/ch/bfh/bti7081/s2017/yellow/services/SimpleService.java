package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import org.hibernate.Criteria;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface SimpleService<T extends BaseBean<? extends Storable>> {

    List<T> getALlEntities();

    List<T> findEntities(Criteria criteria);

    void saveEntities(List<T> entity);
    void saveEntity(T entity);
}
