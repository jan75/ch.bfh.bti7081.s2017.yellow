package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.*;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;
import com.vaadin.data.provider.DataProvider;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by simon on 15.05.17.
 */
public class ContactService extends SimpleServiceImpl<ContactBook, ContactBookBean>  {

    //public List<ContactBookEntryBean> contactList = new ArrayList<>();
    private static boolean init = false;

    private String filter;

    public ContactService() {
        super(ContactBook.class, ContactBookBean.class);

        if (init == false) {
            ContactBookBean contactBookBean = new ContactBookBean();
            ContactBookEntryBean entryPatientBean = new ContactBookEntryBean();
            ContactBookEntryBean entryEmployeeBean = new ContactBookEntryBean();

            PatientBean patient = new PatientBean();
            EmployeeBean employee = new EmployeeBean();
            patient.setFirstName("jöggu");
            patient.setLastName("hugo");
            Date localDate = new Date();
            patient.setCheckInDate(localDate);
            patient.setCheckOutDate(localDate);
            entryPatientBean.setPerson(patient);
            entryPatientBean.setPhoneNr("000000");

            employee.setFirstName("sdg");
            employee.setLastName("g");
            employee.setSince(localDate);
            entryEmployeeBean.setPerson(employee);
            entryEmployeeBean.setPhoneNr("999999");

            contactBookBean.addEntry(entryEmployeeBean);
            contactBookBean.addEntry(entryPatientBean);
            saveEntity(contactBookBean);
            init = true;
        }
    }

    /**
     * Sets a filter for ContactBokEntries.
     * @param filter String which must be contained in any attribute of an ContactBookEntry
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * Returns a list of ContactBookEntries filtered by a string.
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
        return getAllEntities().stream()
                .flatMap(a->a.getEntries().stream())
                .filter(firstNamePredicate)
                .collect(Collectors.toList());
    }

    /**
     * Returns a DataProvider for a List
     * @return
     */
    public DataProvider<ContactBookEntryBean, String> getContactBookDataProvider() {
        return dataProvider;
    }
    DataProvider<ContactBookEntryBean, String> dataProvider = DataProvider.fromFilteringCallbacks(
            // First callback fetches items based on a query
            query -> getContactBookEntries().stream(),

            // Second callback fetches the number of items for a query
            query ->getContactBookEntries().size()
    );

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
