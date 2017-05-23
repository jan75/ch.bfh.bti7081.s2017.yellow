package ch.bfh.bti7081.s2017.yellow.repositories;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepository;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;

public class CrudRepositoryImplTest {
	@Before
	public void before() throws SQLException {
		CrudRepositoryImpl.isDbInitialized = false;
		CrudRepositoryImpl.initDbConnection();
	}
	
	@After
	public void after() {
		CrudRepositoryImpl.shutdown();
	}
	
	/**
	 * Demonstrates saving an entity that has no dependencies to other entities.
	 * This produces the query:
	 * insert into PERSON (FIRST_NAME, LAST_NAME, ID) values (?, ?, ?)
	 */
	@Test
	public void saveAnEntityWithoutRelations() throws SQLException {
		//Get an instance to a repo
		CrudRepository<Person> repo = new CrudRepositoryImpl<>();
		//create the entity
		Person person = new Person("Name1", "Name2");
		repo.save(person);
		CrudRepositoryImpl.shutdown();
	}
	
	/**
	 * Demo for assigning an entity (Person) to another entity (ContactBookEntry).
	 * The relation can be navigation from either side.
	 * This demonstrates the assignment of the ONE (Person) side to the MANY (ContactBookEntry) side.
	 * A contactBookEntry has ONE person, but a Person can be assigned to MANY contactBookEntries.
	 * @throws SQLException 
	 * 
	 *     	@ManyToOne(cascade= {CascadeType.PERSIST})
     *		private Person person;
	 * 
	 * CascadeType.PERSIST results in the persistence of person.
	 * 
	 * Resulting queries:
	 * insert into PERSON (FIRST_NAME, LAST_NAME, ID) values (?, ?, ?)
	 * insert into CONTACT_BOOK_ENTRY (person_ID, PHONE_NR, ID) values (?, ?, ?)
	 * Note that person is saved first.
	 */
	@Test
	public void saveEntityWithManyToOneRelation1() throws SQLException {
		CrudRepositoryImpl.initDbConnection();
		//Get an instance to a repo
		CrudRepository<ContactBookEntry> contactBookEntryRepo = new CrudRepositoryImpl<>();
		//create the MANY entity
		ContactBookEntry contactBookEntry = new ContactBookEntry();
		//create the ONE entity
		Person person = new Person("name1", "name2");
		//assign the ONE entity to the MANY entity
		contactBookEntry.setPerson(person);
		//only save contactBookEntry. Because of CascadeType.PERSIST, person is also saved
		contactBookEntryRepo.save(contactBookEntry);
		CrudRepositoryImpl.shutdown();
	}
	
	/**
	 * Update the DB with the same instance used to save data into the DB.
	 */
	@Test
	public void updateEntity() throws SQLException {
		CrudRepositoryImpl.initDbConnection();
		CrudRepository<Person> repo = new CrudRepositoryImpl<>();
		//create entity
		Person person = new Person("name", "name2");
		//save entity
		repo.save(person);
		//use same instance to change data in DB
		person.setFirstName("other first name");
		repo.save(person);
		CrudRepositoryImpl.shutdown();
	}
}
