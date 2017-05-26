package ch.bfh.bti7081.s2017.yellow.repositories;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import ch.bfh.bti7081.s2017.yellow.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

public class CrudRepositoryImpl<T extends Storable> implements CrudRepository<T> {
	static boolean isDbInitialized = false;
	
	public static void initDbConnection() throws SQLException {
		/*if(isDbInitialized) return;
		isDbInitialized = true;
		Console.main("-tcp", "-pg");
		
		registry = new StandardServiceRegistryBuilder()
				//See org.hibernate.cfg.AvailableSettings for available settings
				.applySetting("hibernate.connection.driver_class", "org.h2.Driver")
				.applySetting("hibernate.connection.url", "jdbc:h2:tcp://localhost/mem:db1")
				.applySetting("hibernate.connection.username", "sa")
				.applySetting("hibernate.connection.password", "")
				.applySetting("hibernate.connection.pool_size", "1")
				.applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
				.applySetting("hibernate.show_sql", "true")
				.applySetting("hibernate.hbm2ddl.auto", "create")
				.build();
		
		sessionFactory = new MetadataSources(registry)
				.addAnnotatedClass(ContactBookEntry.class)
				.addAnnotatedClass(ContactBook.class)
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(NaturalPerson.class)
				.addAnnotatedClass(Patient.class)
				.addAnnotatedClass(Person.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Schedule.class)
				.addAnnotatedClass(ScheduleEntry.class)
				.addAnnotatedClass(Wiki.class)
				.addAnnotatedClass(WikiEntry.class)
				.addAnnotatedClass(PatientEstimation.class)
				.addAnnotatedClass(DailyEstimation.class)
				.buildMetadata()
				.buildSessionFactory();
		entityManager = sessionFactory.createEntityManager();*/
	}
	
	public static void shutdown() {
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}
	
	/**
	 * Flushes and clears the entity manager.
	 * This empties the cache and sends all remaining
	 * queries to the DB. Can be used for unit tests.
	 */
	public static void flush() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.flush();
		session.close();
	}

    @Override
    public List<T> getAll(Class<T> clazz) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<T> result = session.createQuery("from " + clazz.getName(), clazz).list();
		session.getTransaction().commit();
		session.close();
		return result;
    }

    @Override
    public List<T> find(CriteriaQuery<T> criteria) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<T> result = session.createQuery(criteria).getResultList();
		session.getTransaction().commit();
		session.close();

		return result;
    }

    @Override
    public void save(T entity) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(entity);
		session.flush();
		session.getTransaction().commit();
		session.close();
    }

    @Override
    public void update(T entity) {
		save(entity);
    }

    @Override
    public void delete(T entity) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
    }
}
