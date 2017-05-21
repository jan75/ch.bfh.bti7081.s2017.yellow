package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepository;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapper;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapperConsumer;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapperImpl;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleServiceImpl<T extends Storable, B extends BaseBean<T>> implements SimpleService<B>, BeanMapperConsumer<T, B> {

    final protected MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Autowired
    private CrudRepository<T> repo;

    private Class<B> bean;
    private Class<T> entity;


    /*public SimpleServiceImpl() {
        this(null, null);
    }*/

    public SimpleServiceImpl(Class<T> entity, Class<B> bean) {
        this.repo = new CrudRepositoryImpl<>();
        this.bean = bean;
        this.entity = entity;
        BeanMapper mapper = new BeanMapperImpl<>();
        mapper.setBeanMapperConsumer(this);
        mapperFactory.classMap(entity, bean).customize(mapper).register();
    }

    @Override
    public List<B> getALlEntities() {
        List<B> beanList = new ArrayList<>();
        List<T> entityList = new ArrayList<>();
        return mapperFactory.getMapperFacade().map(repo.getAll(entity), beanList.getClass());
    }

    @Override
    public List<B> findEntities(Criteria criteria) {
        List<B> beanList = new ArrayList<>();
        List<T> entityList = new ArrayList<>();
        return mapperFactory.getMapperFacade().map(repo.find(criteria), beanList.getClass());
    }

    @Override
    public void saveEntities(List<B> beans) {

    }

    @Override
    public void saveEntity(B bean) {
        repo.save(mapperFactory.getMapperFacade().map(bean.getEntity(), entity));
    }

    @Override
    public void mapEntityToBean(T entity, B bean) {
        bean.setEntity(entity);
    }

    @Override
    public void mapBeanToEntity(B bean, T entity) {
    }
}
