package ch.bfh.bti7081.s2017.yellow.util;

import ma.glasnost.orika.MappingContext;

/**
 * Created by simon on 21.05.17.
 */
public interface BeanMapperConsumer<A, B> {

    void mapEntityToBean(A entity, B bean);
    void mapBeanToEntity(B bean, A entity);
}
