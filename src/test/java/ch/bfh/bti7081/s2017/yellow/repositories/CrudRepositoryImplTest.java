package ch.bfh.bti7081.s2017.yellow.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import ch.bfh.bti7081.s2017.yellow.entities.person.User;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector.DbTask;

public class CrudRepositoryImplTest {
	@Before
	public void before() throws SQLException {
		DbConnector.isDbInitialized = false;
		DbConnector.initDbConnection();
	}
	
	@After
	public void after() {
		DbConnector.shutdown();
	}
	
	/**
	 * Demonstrates saving an entity that has no dependencies to other entities.
	 * This produces the query:
	 * insert into PERSON (FIRST_NAME, LAST_NAME, ID) values (?, ?, ?)
	 */
	@Test
	public void saveAnEntityWithoutRelations() throws SQLException {
		System.out.println("-----------saveAnEntityWithoutRelations-------------------------");
		//Start new Db Task
		DbTask dbTask = new DbTask();
		
		//create the entity
		Person person = new Person("Name1", "Name2");
		
		//save entity
		dbTask.save(person);
		
		//close session. It is only now that the sql statements are sent to the DB
		dbTask.end();
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
		System.out.println("-----------saveEntityWithManyToOneRelation1-------------------------");
		//Get a session
		DbTask dbTask = new DbTask();
		
		//create the MANY entity
		ContactBookEntry contactBookEntry = new ContactBookEntry();
		
		//create the ONE entity
		Person person = new Person("name1", "name2");
		
		//assign the ONE entity to the MANY entity
		contactBookEntry.setPerson(person);

		//only save contactBookEntry. Because of CascadeType.PERSIST, person is also saved
		dbTask.save(contactBookEntry);

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
	public void updateEntity() throws SQLException {
		System.out.println("-----------updateEntity-------------------------");
		//open a session
		DbTask dbTask = new DbTask();
		
		//create entity
		Person person = new Person("name", "name2");
		
		//save entity
		dbTask.save(person);
		
		//use same instance to change data in DB
		person.setFirstName("other first name");
		dbTask.getSession().save(person);
		
		dbTask.end();
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
	public void saveEntityWithManyToOneRelation2() throws SQLException{
		System.out.println("-----------saveEntityWithManyToOneRelation2-------------------------");
		//Get an instance of a repo
		DbTask dbTask = new DbTask();
		
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
		dbTask.save(person);
		
		dbTask.end();
	}
	
	@Test
	public void createNewAddToListLoadUpdateOneEntry() throws SQLException {
		System.out.println("-----------createNewAddToListLoadUpdateOneEntry-------------------------");
		//Create contactbook
		ContactBook contactBook = new ContactBook();
		
		//add an entry
		ContactBookEntry contactBookEntry1 = new ContactBookEntry();
		contactBookEntry1.setPhoneNr("phone nr");
		List<ContactBookEntry> contactBookEntries = new ArrayList<>();
		contactBookEntries.add(contactBookEntry1);
		contactBook.setEntries(contactBookEntries);
		
		//save
		DbTask dbTask = new DbTask();
		
		dbTask.save(contactBook);
		
		dbTask.end();
		
		//Suppose we load the first entry of the contactBook at a later time
		DbTask dbTask2 = new DbTask();
		ContactBookEntry loadedContactBookEntry = dbTask2.getSession().find(ContactBookEntry.class, contactBookEntry1.getId());
		Assert.assertEquals(contactBookEntry1.getPhoneNr(), loadedContactBookEntry.getPhoneNr());
		dbTask2.end();
	}
}
