package ch.bfh.bti7081.s2017.yellow.repositories;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.person.NaturalPerson;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.entities.person.User;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.DailyEstimation;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.PatientEstimation;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;

import org.h2.tools.Console;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * This class is responsible for the configuration 
 * and establishment of the database connection.
 * It provides methods and classes to initialize, use and shutdown the connection.
 * 
 * @author Marc
 *
 */
public class DbConnector {
	
	//These properties are static to emphasize their
	//singleton nature. They are initialized once
	//at runtime.
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private static boolean isDbInitialized = false;

	/**
	 * Creates new DbTasks. Can be overridden 
	 * in sub classes to return a DbTask
	 * that returns arbitrary dummy data
	 * which is useful for unit tests.
	 * @return a new DbTask
	 */
	public DbTask createDbTask() {
		return new DbTask();
	}
	
	/**
	 * Every operation on the database can be done
	 * with an instance of this class. It hides
	 * details of the Hibernate interface and provides
	 * methods to easily save/update and load entity objects. 
	 * These convenience methods open a session and transaction
	 * if they haven't been opened already.
	 * If more complex operations are needed, the underlying
	 * session or transaction instances can be accessed or created.
	 *
	 * Create a database task by using the factory method
	 * createDbTask() of the DbConnector and end the task 
	 * by calling end() on the DbTask instance. The operation is only
	 * committed to the database after end() was called.
	 * 
	 * For simplicity, the transaction and session is created together
	 * when a convenience method or start() is called.
	 */
	public static class DbTask {
		private Session session;
		private Transaction transaction;
		
		public Session getSession() {
			return session;
		}
		public Transaction getTransaction() {
			return transaction;
		}
		
		/**
		 * Also called by convenience methods.
		 * Ensures that a transaction is opened.
		 */
		public void start() {
			if(this.transaction == null) {
				this.session = sessionFactory.openSession();
				this.transaction = this.session.beginTransaction();
			}
		}

		/**
		 * Returns all entity objects of a given class.
		 * @param clazz of the entity objects that will be retrieved from the database
		 * @return a list of all entity objects of the given class.
		 */
		public List findAll(Class clazz) {
			start();
			return this.session.createQuery("from " + clazz.getName()).list();
		}
		
		/**
		 * Returns one entity by id.
		 * @param clazz type of the entity
		 * @param primaryKey of the entity
		 * @return the entity
		 */
		public <T> T find(Class<T> clazz, Object primaryKey) {
			start();
			return this.session.find(clazz, primaryKey);
		}
		
		/**
		 * Creates new database entries or 
		 * updates existing ones depending on whether the ID
		 * is set or not. Use the annotation attribute cascade=CascadeType.ALL
		 * to cascade CRUD operations to related entities.
		 * @param o Object to synchronize with database
		 */
		public void save(Storable o) {
			start();
			if(o.getId() == null) {
				getSession().persist(o);
			} else {
				getSession().merge(o);
			}
		}
		
		/**
		 * Ends the transaction and commits changes to the database.
		 */
		public void end() {
			this.getTransaction().commit();
			this.getSession().close();
		}
	}
	
	/**
	 * Initializes the database connection. 
	 * Does nothing if it has already been initialized.
	 * @throws SQLException
	 */
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
				.applySetting("hibernate.connection.pool_size", "100")
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
				.addAnnotatedClass(Wiki.class)
				.addAnnotatedClass(WikiEntry.class)
				.addAnnotatedClass(DailyEstimation.class)
				.addAnnotatedClass(PatientEstimation.class)
				.buildMetadata()
				.buildSessionFactory();
	}
	
	/**
	 * Closes the database connection.
	 */
	public static void shutdown() {
		sessionFactory.close();
		isDbInitialized = false;
	}
}
