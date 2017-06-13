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
 * Created by simon on 12.06.17.
 */
public class DbConnectorMock extends DbConnector {

    @Override
    public DbTask createDbTask() {
        return new DbTaskMock();
    }

    public class DbTaskMock extends DbConnector.DbTask {
        /**
         * Creating an instance directly opens
         * a Hibernate session and transaction.
         */

        protected DbTaskMock() {

        }

        @Override
        public Session getSession() {
            return null;
        }

        @Override
        public void setSession(Session session) {

        }

        @Override
        public Transaction getTransaction() {
            return null;
        }

        @Override
        public void setTransaction(Transaction transaction) {

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