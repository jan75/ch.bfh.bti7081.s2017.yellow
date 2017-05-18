package ch.bfh.bti7081.s2017.yellow;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepository;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;

public class CrudRepositoryImplTest {

	@Test
	public void dbTest() throws SQLException {
		CrudRepositoryImpl.initDbConnection();
		
		CrudRepository<ContactBookEntry> crud = new CrudRepositoryImpl<>();
		CrudRepository<Person> crud2 = new CrudRepositoryImpl<>();
		Person person = new Person("name", "name2");
		crud2.save(person);
		ContactBookEntry entry = new ContactBookEntry(person, "079234987234987234");
		crud.save(entry);
		
		List<ContactBookEntry> list = crud.getAll(ContactBookEntry.class);
		
		Assert.assertTrue(list.get(0).getPhoneNr().equals("079234987234987234") &&
				list.get(0).getPerson().getFirstName().equals("name"));
		
		CrudRepositoryImpl.shutdown();
	}
}
