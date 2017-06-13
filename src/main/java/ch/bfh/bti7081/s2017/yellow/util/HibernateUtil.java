package ch.bfh.bti7081.s2017.yellow.util;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.*;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.DailyEstimation;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.PatientEstimation;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.Wiki;
import ch.bfh.bti7081.s2017.yellow.entities.wiki.WikiEntry;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

/**
 * Created by samuel on 25.05.17.
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        Configuration cnf = new Configuration();
        cnf.setProperty(Environment.DRIVER, "org.h2.Driver");
        cnf.setProperty(Environment.URL, "jdbc:h2:tcp://localhost/mem:db1");
        cnf.setProperty(Environment.USER, "sa");
        cnf.setProperty(Environment.PASS, "");
        cnf.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        cnf.setProperty(Environment.SHOW_SQL, "true");
        cnf.setProperty(Environment.HBM2DDL_AUTO, "create");
        cnf.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        cnf.addAnnotatedClass(ContactBookEntry.class)
                .addAnnotatedClass(ContactBook.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(NaturalPerson.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Schedule.class)
                .addAnnotatedClass(Wiki.class)
                .addAnnotatedClass(WikiEntry.class)
                .addAnnotatedClass(PatientEstimation.class)
                .addAnnotatedClass(DailyEstimation.class);

        SESSION_FACTORY = cnf.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
