package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookBean;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import ch.bfh.bti7081.s2017.yellow.mock.DbConnectorMock;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import javassist.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by simon on 12.06.17.
 */
public class ContactServiceTest {
    @Test
    public void saveContact() throws Exception {

        // Instantiate a service with mock data
        ContactService service = new ContactService(new DbMock());

        // Load mock data
        service.LoadContactBook();

        // Test exception "NotFoundException"
        try {
            service.saveContact(new ContactBookEntryBean());
        }
        catch (NotFoundException e) {
            // Test is successfull
        }

        // get the first entry
        ContactBookEntryBean oneEntry = (ContactBookEntryBean)service.getContactBookBean().getEntries().stream().findFirst().get();

        // test save function
        service.saveContact(oneEntry);
    }

    @Test
    public void loadContactBook() throws Exception {
        // Instantiate a service with mock data
        ContactService service = new ContactService(new DbMock());

        // Load mock data
        service.LoadContactBook();
    }

    @Test
    public void getContactBookEntries() throws Exception {

        // Instantiate a service with mock data
        ContactService service = new ContactService(new DbMock());

        // Load mock data
        service.LoadContactBook();

        // get all entries
        assertFalse(service.getContactBookEntries().isEmpty());

        // get a certain entry
        service.setFilter("");
        assertTrue(service.getContactBookEntries().get(0).getPerson().getFirstName() == "test1");
    }


    public class DbMock extends DbConnectorMock {

        @Override
        public DbTask createDbTask() {
            return new TaskMock();
        }

        public class TaskMock extends DbConnectorMock.DbTaskMock {

            @Override
            public List findAll(Class clazz) {
                ContactBook contactBook = new ContactBook();
                ContactBookEntry entry1 = new ContactBookEntry();
                ContactBookEntry entry2 = new ContactBookEntry();
                contactBook.addEntry(entry1);
                contactBook.addEntry(entry2);

                Employee employee = new Employee();
                employee.setFirstName("test1");
                entry1.setPerson(employee);

                Patient patient = new Patient();
                patient.setFirstName("test2");
                entry2.setPerson(patient);

                return Arrays.asList(contactBook);
            }
        }
    }
}