package ch.bfh.bti7081.s2017.yellow.mock;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;

/**
 * Used to generate mock data for tests
 * @author iSorp
 */
public class DbConnectorMock extends DbConnector {

    @Override
    public DbTask createDbTask() {
        return new DbTaskMock();
    }

    public class DbTaskMock extends DbConnector.DbTask {

        @Override
        public Session getSession() {
            return null;
        }

        @Override
        public Transaction getTransaction() {
            return null;
        }

        @Override
        public List findAll(Class clazz) {
            return null;
        }

        @Override
        public void save(Storable o) {

        }

        @Override
        public void end() {

        }
    }
}