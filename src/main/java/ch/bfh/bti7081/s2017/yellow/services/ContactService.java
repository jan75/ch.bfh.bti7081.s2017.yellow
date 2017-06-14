package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.*;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import javassist.NotFoundException;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service for contactBook data access. Supplies loading, saving, mapping and filtering of contactBook data.
 * @author iSorp
 */
public class ContactService extends SimpleServiceImpl<ContactBook, ContactBookBean>  {

    //public List<ContactBookEntryBean> contactList = new ArrayList<>();
    private static ContactService instance;
    private ContactBookBean contactBookBean;
    private SimpleService contactEntryService;
    private String filter;

    /**
     * Initialize a new default ContactService instance
     */
    public ContactService() {
        super(ContactBook.class, ContactBookBean.class, new DbConnector());
        contactEntryService = new SimpleServiceImpl<ContactBookEntry, ContactBookEntryBean>(ContactBookEntry.class, ContactBookEntryBean.class, new DbConnector());
        if (instance == null) {
            try {
                createDummyContactBook();
            }catch (Exception excepotion) { }
            instance = this;
        }
    }

    /**
     * Initialize a new ContactService instance with a certain DbConnector
     * @param dbConnector
     */
    public ContactService(DbConnector dbConnector) {
        super(ContactBook.class, ContactBookBean.class, dbConnector);
        contactEntryService = new SimpleServiceImpl<ContactBookEntry, ContactBookEntryBean>(ContactBookEntry.class, ContactBookEntryBean.class, dbConnector);
        if (instance == null) {
            try {
                createDummyContactBook();
            }catch (Exception excepotion) { }
            instance = this;
        }
    }

    /**
     * Saves a edited contactBookEntry to the database.
     * @param contact contactBookEntryBean
     * @throws NotFoundException throws an Exception if the related entity is not found in the database.
     */
    public void saveContact(ContactBookEntryBean contact) throws NotFoundException {
        Optional bean = getContactBookEntries().stream().filter(a -> Objects.equals(a.getId(),contact.getId())).findFirst();

        if (bean.isPresent()) {
            contactEntryService.saveEntity(contact);
        }
        else {
            throw new NotFoundException("edited contact was not found in database");
        }
    }

    /**
     * The Methode loads all contactBook entities from the database.
     */
    public void LoadContactBook(){
        contactBookBean = getALlEntities().get(0);

        // Create empty contactBook
        if(contactBookBean == null) {
            contactBookBean = new ContactBookBean();
        }
    }


    /**
     * Returns the current loaded contacBookBean.
     * @return ContactBookBean
     */
    public ContactBookBean getContactBookBean() {
        return contactBookBean;
    }

    /**
     * Sets a filter for ContactBokEntries.
     * @param filter String which must be contained in any attribute of an ContactBookEntry
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * Returns a list of ContactBookEntries filtered by a argument.
     * @return list of ContactBookEntries
     */
    public List<ContactBookEntryBean> getContactBookEntries(){

        // Predicate for filter
        Predicate<ContactBookEntryBean> firstNamePredicate = a -> {
            if (filter == null || filter == "")
                return true;
            return a.getPerson().getFirstName().contains(filter) || a.getPerson().getLastName().contains(filter);
        };
        // Mapping of a ContactBook list to a list of ContactBookEntry list
        return contactBookBean.getEntries().stream()
                .filter(firstNamePredicate)
                .collect(Collectors.toList());
    }


    /**
     * The Method is used to do post mapping work after a entity has been mapped to a bean.
     * It mapps nested objects to a certain type.
     * @param entity
     * @param bean
     */
    @Override
    public void mapEntityToBean(ContactBook entity, ContactBookBean bean) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        for (int i = 0; i < entity.getEntries().size(); i++) {
            ContactBookEntry e = entity.getEntries().get(i);
            ContactBookEntryBean b = bean.getEntries().get(i);

            if (e.getPerson() instanceof Patient){
                b.setPerson(new PatientBean());
            } else if (e.getPerson() instanceof Employee){
                b.setPerson(new EmployeeBean());
            }

            mapperFactory.classMap(e.getPerson().getClass(), b.getPerson().getClass()).byDefault();
            mapperFactory.getMapperFacade().map(e.getPerson(), b.getPerson());
        }
    }

    /**
     * The Method is used to do post mapping work after a bean has been mapped to a entity.
     * It mapps nested objects to a certain type.
     * @param bean
     * @param entity
     */
    @Override
    public void mapBeanToEntity(ContactBookBean bean, ContactBook entity) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        for (int i = 0; i < entity.getEntries().size(); i++) {
            ContactBookEntry e = entity.getEntries().get(i);
            ContactBookEntryBean b = bean.getEntries().get(i);
            if (b.getPerson() instanceof PatientBean){
                e.setPerson(new Patient());
            } else if (b.getPerson() instanceof EmployeeBean){
                e.setPerson(new Employee());
            }
            mapperFactory.classMap(b.getPerson().getClass(), e.getPerson().getClass()).byDefault();
            mapperFactory.getMapperFacade().map(b.getPerson(), e.getPerson());
        }
    }



    /**
     * Creates dummy data for a ContactBook.
     */
    private static void createDummyContactBook() {
        DbConnector.DbTask task = new DbConnector().createDbTask();
        try {
            // dummy Employee
            Employee employee1 = new Employee();
            employee1.setFirstName("Samuel");
            employee1.setLastName("Hacker");
            employee1.setSince(new Date());
            task.save(employee1);

            Employee employee2 = new Employee();
            employee2.setFirstName("Dario");
            employee2.setLastName("D.A.U.");
            Date date = new Date();
            date.setTime(2000);
            employee2.setSince(date);
            task.save(employee2);

            // dummy Patient
            Patient patient1 = new Patient();
            patient1.setFirstName("Husten");
            patient1.setLastName("Malaria");
            patient1.setCheckInDate(date);
            patient1.setCheckOutDate(new Date());
            task.save(patient1);

            Patient patient2 = new Patient();
            patient2.setFirstName("Bandwurm");
            patient2.setLastName("Darmverschuss");
            patient2.setCheckInDate(new Date());
            patient2.setCheckOutDate(new Date());
            task.save(patient2);

            // dummy ContactBookEntry
            List<Person> persList = task.findAll(Person.class);
            ContactBookEntry contactBookEntryEmployee1 = new ContactBookEntry();
            contactBookEntryEmployee1.setPerson(persList.get(0));
            task.save(contactBookEntryEmployee1);

            ContactBookEntry contactBookEntryEmployee2 = new ContactBookEntry();
            contactBookEntryEmployee2.setPerson(persList.get(1));
            task.save(contactBookEntryEmployee2);

            // dummy ContactBookEntry
            ContactBookEntry contactBookEntryPatient1 = new ContactBookEntry();
            contactBookEntryPatient1.setPerson(persList.get(2));
            task.save(contactBookEntryPatient1);

            ContactBookEntry contactBookEntryPatient2 = new ContactBookEntry();
            contactBookEntryPatient2.setPerson(persList.get(3));
            task.save(contactBookEntryPatient2);

            // dummy ContactBoo
            List<ContactBookEntry> entryList = task.findAll(ContactBookEntry.class);
            ContactBook contactBook = new ContactBook();
            contactBook.addEntry(entryList.get(0));
            contactBook.addEntry(entryList.get(1));
            contactBook.addEntry(entryList.get(2));
            contactBook.addEntry(entryList.get(3));
            task.save(contactBook);
        }
        finally {
            task.end();
        }
    }
}
