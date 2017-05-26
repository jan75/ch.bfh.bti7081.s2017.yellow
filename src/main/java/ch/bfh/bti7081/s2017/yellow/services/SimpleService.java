package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface SimpleService<E extends Storable, B extends BaseBean<? extends Storable>> {

    List<B> getAllEntities();

    List<B> findEntities(CriteriaQuery<E> criteria);

    void saveEntities(List<B> entity);
    void saveEntity(B entity);
}
