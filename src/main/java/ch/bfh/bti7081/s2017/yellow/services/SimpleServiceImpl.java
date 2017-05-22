package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookBean;
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
import java.util.stream.Collectors;

@Service
public class SimpleServiceImpl<A extends Storable, B extends BaseBean<A>> implements SimpleService<B>, BeanMapperConsumer<A, B> {

    final protected MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    BeanMapper mapper = new BeanMapperImpl<A, B>();

    @Autowired
    protected CrudRepository<A> repo;

    private Class<A> entityClazz;
    private Class<B> beanClazz;

    public SimpleServiceImpl(Class<A> entity, Class<B> bean) {
        this.repo = new CrudRepositoryImpl<>();
        this.beanClazz = bean;
        this.entityClazz = entity;
        mapperFactory.classMap(ContactBook.class, ContactBookBean.class).customize(mapper).register();
        mapper.setBeanMapperConsumer(this);
    }

    @Override
    public List<B> getALlEntities() {
        return mapperFactory.getMapperFacade().mapAsList(repo.getAll(entityClazz), beanClazz);
    }

    @Override
    public List<B> findEntities(Criteria criteria) {
        return mapperFactory.getMapperFacade().mapAsList(repo.find(criteria), beanClazz);
    }

    @Override
    public void saveEntities(List<B> beans) {
        for (B b : beans) {
            if (b.getId() == null) {
                repo.save(mapperFactory.getMapperFacade().map(beanClazz, entityClazz));
            } else {
                repo.update(mapperFactory.getMapperFacade().map(beanClazz, entityClazz));
            }
        }
    }

    @Override
    public void saveEntity(B bean) {
        if (bean.getId() == null) {
            repo.save(mapperFactory.getMapperFacade().map(bean, entityClazz));
        } else {
            repo.update(mapperFactory.getMapperFacade().map(bean, entityClazz));
        }
    }

    @Override
    public void mapEntityToBean(A entity, B bean) {
        //bean.(entity);
    }

    @Override
    public void mapBeanToEntity(B bean, A entity) {
    }
}
