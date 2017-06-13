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

	public DbTask createDbTask() {
		return new DbTask();
	}
	
	/**
	 * Every operation on the database can be done
	 * with an instance of this class. It hides
	 * details of the Hibernate interface and provides
	 * methods to easily save/update and load entity objects.
	 * If more complex operations are needed, the underlying
	 * session or transaction instances can be accessed.
	 *
	 * Start a database task by creating an instance of DbTask
	 * and end the task by calling end(). The operation is only
	 * committed to the database after end() was called.
	 */
	public static class DbTask {
		private Session session;
		private Transaction transaction;
		
		/**
		 * Creating an instance directly opens 
		 * a Hibernate session and transaction.
		 */
		protected DbTask() {
		}
		
		public Session getSession() {
			if (this.session == null) {
				this.session = sessionFactory.openSession();
			}
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		public Transaction getTransaction() {
			if (transaction == null) {
				this.transaction = getSession().beginTransaction();
			}
			return transaction;
		}
		public void setTransaction(Transaction transaction) {
			this.transaction = transaction;
		}
		
		/**
		 * Returns all entity objects of a given class.
		 * @param clazz of the entity objects that will be retrieved from the database
		 * @return a list of all entity objects of the given class.
		 */
		public List findAll(Class clazz) {
			return getSession().createQuery("from " + clazz.getName()).list();
		}
		
		/**
		 * Creates new database entries or 
		 * updates existing ones depending on whether the ID
		 * is set or not. Use the annotation attribute cascade=CascadeType.ALL
		 * to cascade CRUD operations to related entities.
		 * @param o Object to synchronize with database
		 */
		public void save(Storable o) {
			if(o.getId() == null) {
				getSession().persist(o);
			} else {
				getSession().merge(o);
			}
		}
		
		/**
		 * Ends the transactions and commits changes to the database.
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
