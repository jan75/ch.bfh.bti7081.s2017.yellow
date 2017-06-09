package ch.bfh.bti7081.s2017.yellow.util;

import ma.glasnost.orika.Mapper;

/**
 * BeanMapper supplies to map entities to beans and oposite.
 *
 * @author iSorp
 * @see BeanMapperImpl
 */
public interface BeanMapper<A, B> extends Mapper<A, B> {
    void setBeanMapperConsumer(BeanMapperConsumer beanMapperConsumer);
}
