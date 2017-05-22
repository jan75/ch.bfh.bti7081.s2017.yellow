package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

/**
 * Created by simon on 20.05.17.
 */
public class BaseBean<E extends Storable> {
    protected E entity;

    public void setEntity(E entity) {
        this.entity = entity;
    }
    public E getEntity() {
        return entity;
    }
}
