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
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;
import java.util.List;

public class DbConnector {
	private static StandardServiceRegistry registry;
	protected static SessionFactory sessionFactory;
	static boolean isDbInitialized = false;
	
	public static class DbTask {
		private Session session;
		private Transaction transaction;
		
		public DbTask() {
			this.session = sessionFactory.openSession();
			this.transaction = session.beginTransaction();
		}
		
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		public Transaction getTransaction() {
			return transaction;
		}
		public void setTransaction(Transaction transaction) {
			this.transaction = transaction;
		}
		
		public List findAll(Class clazz) {
			return session.createQuery("from " + clazz.getName()).list();
		}
		
		public void save(Storable o) {
			if(o.getId() == null) {
				session.persist(o);
			} else {
				session.merge(o);
			}
		}
		
		public void end() {
			this.transaction.commit();
			this.session.close();
		}
	}
	
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
	
	public static void shutdown() {
		sessionFactory.close();
		isDbInitialized = false;
	}
}
