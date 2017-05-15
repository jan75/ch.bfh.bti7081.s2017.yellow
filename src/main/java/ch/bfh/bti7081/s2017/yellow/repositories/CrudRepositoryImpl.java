package ch.bfh.bti7081.s2017.yellow.repositories;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class CrudRepositoryImpl<T extends Storable> implements CrudRepository<T> {
	private static final StandardServiceRegistry registry;
	private static final SessionFactory sessionFactory;
	private static final EntityManager entityManager;
	
	static {
		registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		sessionFactory = new MetadataSources(registry)
				.buildMetadata()
				.buildSessionFactory();
		entityManager = sessionFactory.createEntityManager();
	}
	
    @Override
    public List<T> getAll(Class<T> clazz) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<T> result = session.createQuery("from " + clazz.getName(), clazz).list();
		session.getTransaction().commit();
		session.close();
		return result;
    }

    @Override
    public List<T> find(Criteria criteria) {
        return null;
    }

    @Override
    public void save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist( entity );
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }
}
