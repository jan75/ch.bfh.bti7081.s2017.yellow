package ch.bfh.bti7081.s2017.yellow.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
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
	@Ignore
	public void saveAnEntityWithoutRelations() throws SQLException {
		System.out.println("-----------saveAnEntityWithoutRelations-------------------------");
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
	@Ignore
	public void saveEntityWithManyToOneRelation1() throws SQLException {
		System.out.println("-----------saveEntityWithManyToOneRelation1-------------------------");
		CrudRepositoryImpl.initDbConnection();
		//Get an instance of a repo
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
	 * Creates the following queries:
	 * insert into PERSON (FIRST_NAME, LAST_NAME, ID) values (?, ?, ?)
	 * update PERSON set FIRST_NAME=? where ID=?
	 * 
	 * Note that the query would look like this if @DynamicUpdate on the Person entity
	 * is omitted:
     * update PERSON set FIRST_NAME=?, LAST_NAME=? where ID=?
     * With @DynamicUpdate, Hibernate adds only the changed columns to the query.
	 */
	@Test
	@Ignore
	public void updateEntity() throws SQLException {
		System.out.println("-----------updateEntity-------------------------");
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
	
	/**
	 * Same as saveEntityWithManyToOneRelation1, but this time,
	 * the relation is saved the other way. Instead of
	 * adding the person the the ContactBookEntry,
	 * the contactBookEntry is added to the person.
	 * 
	 * Note the new attribute "mappedBy". If set, Hibernate knows to
	 * add a foreign key to the table CONTACT_BOOK_ENTRY. Otherwise,
	 * Hibernate creates an intermediate table.
	 *    
     *   @OneToMany(cascade= {CascadeType.PERSIST}, mappedBy="person")
     *   private List<ContactBookEntry> contactBookEntries;
     *   
	 * Resulting query:
	 * insert into PERSON (FIRST_NAME, LAST_NAME, ID) values (?, ?, ?)
     * insert into CONTACT_BOOK_ENTRY (person_ID, PHONE_NR, ID) values (?, ?, ?)
     * 
     * 
	 * @throws SQLException 
	 */
	@Test
	@Ignore
	public void saveEntityWithManyToOneRelation2() throws SQLException{
		System.out.println("-----------saveEntityWithManyToOneRelation2-------------------------");
		CrudRepositoryImpl.initDbConnection();
		//Get an instance of a repo
		CrudRepository<Person> personRepo = new CrudRepositoryImpl<>();
		//create the MANY entity
		ContactBookEntry contactBookEntry = new ContactBookEntry();
		//create the ONE entity
		Person person = new Person("name1", "name2");
		//assign the MANY entity to the ONE entity
		contactBookEntry.setPerson(person);
		List<ContactBookEntry> contactBookEntries = new ArrayList<>();
		contactBookEntries.add(contactBookEntry);
		person.setContactBookEntries(contactBookEntries);
		//only save contactBookEntry. Because of CascadeType.PERSIST, person is also saved
		personRepo.save(person);
		CrudRepositoryImpl.shutdown();
	}
	
	@Test
	@Ignore
	public void createNewAddToListLoadUpdateOneEntry() throws SQLException {
		System.out.println("-----------createNewAddToListLoadUpdateOneEntry-------------------------");
		CrudRepositoryImpl.initDbConnection();
		//contactbook, entry. dann laden entries. ein zweiter entry.
		//wieder laden, ein entry laden -> ging nicht
		
		//Create contactbook
		ContactBook contactBook = new ContactBook();
		//add an entry
		ContactBookEntry contactBookEntry1 = new ContactBookEntry();
		contactBookEntry1.setPhoneNr("phone nr");
		List<ContactBookEntry> contactBookEntries = new ArrayList<>();
		contactBookEntries.add(contactBookEntry1);
		contactBook.setEntries(contactBookEntries);
		//save
		CrudRepository<ContactBook> contactBookRepo = new CrudRepositoryImpl<>();
		contactBookRepo.save(contactBook);
		//load
		ContactBook loadedContactBook = contactBookRepo.getAll(ContactBook.class).get(0);
		//get first entry
		ContactBookEntry loadedContactBookEntry = loadedContactBook.getEntries().get(0);
		//update this entry
		loadedContactBookEntry.setPhoneNr("other phone number");
		CrudRepositoryImpl.shutdown();
	}
}
