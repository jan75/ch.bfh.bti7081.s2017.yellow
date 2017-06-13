package ch.bfh.bti7081.s2017.yellow.util;

import ma.glasnost.orika.MappingContext;

/**
 * Interface for a BeanMapper consumer. It allows the BeanMapper to notify listeners about a completed mapping action.
 *
 * @author iSorp
 * @see BeanMapperImpl
 */
public interface BeanMapperConsumer<A, B> {
    void mapEntityToBean(A entity, B bean);
    void mapBeanToEntity(B bean, A entity);
}
