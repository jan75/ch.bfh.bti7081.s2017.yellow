package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookBean;
import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector.DbTask;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapper;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapperConsumer;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapperImpl;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleServiceImpl<A extends Storable, B extends BaseBean<A>> implements SimpleService<B>, BeanMapperConsumer<A, B> {

    final protected MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    BeanMapper mapper = new BeanMapperImpl<A, B>();

    @Autowired
    protected Session session;

    private Class<A> entityClazz;
    private Class<B> beanClazz;

    public SimpleServiceImpl(Class<A> entity, Class<B> bean) {
        this.beanClazz = bean;
        this.entityClazz = entity;
        mapperFactory.classMap(ContactBook.class, ContactBookBean.class).customize(mapper).register();
        mapper.setBeanMapperConsumer(this);
    }

    @Override
    public List<B> getALlEntities() {
    	DbTask d = new DbTask();
    	List l = d.findAll(entityClazz);
    	List<B> list = mapperFactory.getMapperFacade().mapAsList(l, beanClazz);
    	d.end();
        return list;
    }

    @Override
    public List<B> findEntities(Criteria criteria) {
        return mapperFactory.getMapperFacade().mapAsList(criteria.list(), beanClazz);
    }

    @Override
    public void saveEntities(List<B> beans) {
    	DbTask d = new DbTask();
        for (B b : beans) {
            d.save(mapperFactory.getMapperFacade().map(beanClazz, entityClazz));
        }
        d.end();
    }

    @Override
    public void saveEntity(B bean) {
    	DbTask d = new DbTask();
    	d.save(mapperFactory.getMapperFacade().map(beanClazz, entityClazz));
    	d.end();
    }

    @Override
    public void mapEntityToBean(A entity, B bean) {
        //bean.(entity);
    }

    @Override
    public void mapBeanToEntity(B bean, A entity) {
    }
}
