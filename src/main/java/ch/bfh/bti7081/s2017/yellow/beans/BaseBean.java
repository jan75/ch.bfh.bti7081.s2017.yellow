package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

/**
 * Created by simon on 20.05.17.
 */
public class BaseBean<E extends Storable> {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
