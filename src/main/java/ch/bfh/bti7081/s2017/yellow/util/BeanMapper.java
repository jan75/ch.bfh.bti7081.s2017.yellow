package ch.bfh.bti7081.s2017.yellow.util;

import ma.glasnost.orika.Mapper;

/**
 * Created by simon on 21.05.17.
 */
public interface BeanMapper<A, B> extends Mapper<A, B> {
    void setBeanMapperConsumer(BeanMapperConsumer beanMapperConsumer);
}
