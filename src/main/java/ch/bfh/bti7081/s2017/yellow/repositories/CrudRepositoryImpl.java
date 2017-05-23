package ch.bfh.bti7081.s2017.yellow.repositories;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.person.NaturalPerson;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.entities.person.User;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.ScheduleEntry;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;

import org.h2.tools.Console;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;

public class CrudRepositoryImpl<T extends Storable> implements CrudRepository<T> {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private static EntityManager entityManager;
	static boolean isDbInitialized = false;
	private static Console console;
	
	public static void initDbConnection() throws SQLException {
		if(isDbInitialized) return;
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
				.buildMetadata()
				.buildSessionFactory();
		entityManager = sessionFactory.createEntityManager();
	}
	
	public static void shutdown() {
		entityManager.close();
		sessionFactory.close();
	}
	
	/**
	 * Flushes and clears the entity manager.
	 * This empties the cache and sends all remaining
	 * queries to the DB. Can be used for unit tests.
	 */
	public static void flush() {
		entityManager.flush();
		entityManager.clear();
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
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }
}
