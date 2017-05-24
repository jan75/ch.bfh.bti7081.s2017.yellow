package ch.bfh.bti7081.s2017.yellow.util;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by simon on 20.05.17.
 */
public class BeanMapper<T extends Storable, R extends BaseBean> {
    protected static MapperFactory mapperFactory;

    /**
     *
     * @param entity
     * @param bean
     * @return R
     */
    public R getEntityBean(T entity, R bean) {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(entity.getClass(), bean.getClass())
                .mapNulls(false).mapNullsInReverse(false)
                .byDefault()
                .register();
        mapperFactory.getMapperFacade().map(entity, bean);
        bean.setEntity(entity);
        return bean;
    }
}
