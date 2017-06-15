package ch.bfh.bti7081.s2017.yellow.util;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * This is a bean ma.glasnost.orika custom mapper. Only the properties having same names are copied.
 * If nested objects are supposed to be mapped, please implement a BeanMapper interface to do it manually.
 * @see BeanMapper
 * @param <A> Entity
 * @param <B> Bean
 */
public class BeanMapperImpl<A extends Storable, B extends BaseBean> extends CustomMapper<A, B> implements BeanMapper<A, B> {

    private BeanMapperConsumer consumer;

    /**
     * Maps an entity to a bean
     * @param bean
     * @param entity
     */
    @Override
    public void mapAtoB(A entity, B bean, MappingContext context) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(entity.getClass(), bean.getClass()).byDefault();
        mapperFactory.getMapperFacade().map(entity, bean);
        consumer.mapEntityToBean(entity, bean);
    }

    /**
     * Maps a bean to an entity
     * @param entity
     * @param bean
     */
    @Override
    public void mapBtoA(B bean, A entity, MappingContext context) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(bean.getClass(), entity.getClass()).byDefault();
        mapperFactory.getMapperFacade().map(bean, entity);
        consumer.mapBeanToEntity(bean, entity);
    }

    /**
     * Adds a consumer to de context. It gets informed as soon as the mapping process has succeeded
     * @param consumer
     */
    @Override
    public void setBeanMapperConsumer(BeanMapperConsumer consumer) {
        this.consumer = consumer;
    }
}
