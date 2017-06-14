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
     * Creates dummy data for a ContactBook.
     */
    private static void createDummyContactBook() {
        DbConnector.DbTask task = new DbConnector().createDbTask();
        try {
            // dummy Employee
            /* commented out because of conflict with planning utility
            Employee employee = new Employee();
            employee.setFirstName("Samuel");
            employee.setLastName("Hacker");
            employee.setSince(new Date());
            task.save(employee);
            */

            // dummy Patient
            Patient patient = new Patient();
            patient.setFirstName("Hustem");
            patient.setLastName("Malaria");
            patient.setCheckInDate(new Date());
            patient.setCheckOutDate(new Date());
            task.save(patient);

            // dummy ContactBookEntry
            List<Person> persList = task.findAll(Person.class);
            ContactBookEntry contactBookEntryEmployee = new ContactBookEntry();
            //contactBookEntryEmployee.setPerson(persList.get(0));
            task.save(contactBookEntryEmployee);

            // dummy ContactBookEntry
            ContactBookEntry contactBookEntryPatient = new ContactBookEntry();
            contactBookEntryPatient.setPerson(persList.get(0)); // changed to 0
            task.save(contactBookEntryPatient);

            // dummy ContactBoo
            List<ContactBookEntry> entryList = task.findAll(ContactBookEntry.class);
            ContactBook contactBook = new ContactBook();
            //contactBook.addEntry(entryList.get(0));
            contactBook.addEntry(entryList.get(1));
            task.save(contactBook);
        }
        finally {
            task.end();
        }
    }

    /**
     * Saves a edited contactBookEntry to the database.
     * @param contact contactBookEntryBean
     * @throws NotFoundException throws an Exception if the related entity is not found in the database.
     */
    public void saveContact(ContactBookEntryBean contact) throws NotFoundException {
        ContactBookEntryBean bean = getContactBookEntries().stream().filter(a -> Objects.equals(a.getId(),contact.getId())).findFirst().get();

        if (bean != null) {
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
}
