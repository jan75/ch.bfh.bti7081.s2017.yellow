package ch.bfh.bti7081.s2017.yellow.util;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by simon on 20.05.17.
 */
public class BeanMapperImpl<A extends Storable, B extends BaseBean> extends CustomMapper<A, B> implements BeanMapper<A, B> {

    private BeanMapperConsumer consumer;

    /**
     *
     * @param bean
     * @param entity
     * @return
     */
    @Override
    public void mapAtoB(A entity, B bean, MappingContext context) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(entity.getClass(), bean.getClass()).byDefault();
        mapperFactory.getMapperFacade().map(entity, bean);
        consumer.mapEntityToBean(entity, bean);
    }

    /**
     *
     * @param entity
     * @param bean
     * @return
     */
    @Override
    public void mapBtoA(B bean, A entity, MappingContext context) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(entity.getClass(), bean.getClass()).byDefault();
        mapperFactory.getMapperFacade().map(entity, bean);
        consumer.mapBeanToEntity(bean, entity);
    }

    @Override
    public void setBeanMapperConsumer(BeanMapperConsumer consumer) {
        this.consumer = consumer;
    }
}
