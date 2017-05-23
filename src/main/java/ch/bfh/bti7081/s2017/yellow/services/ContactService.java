package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.*;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import com.vaadin.data.provider.DataProvider;

import java.time.LocalDate;
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
            ContactBookEntryBean<PatientBean> entryPatientBean = new ContactBookEntryBean<>();
            ContactBookEntryBean<EmployeeBean> entryEmployeeBean = new ContactBookEntryBean<>();

            PatientBean patient = new PatientBean();
            EmployeeBean employee = new EmployeeBean();
            patient.setFirstName("jöggu");
            patient.setLastName("hugo");
            LocalDate localDate = LocalDate.now();
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
        return getALlEntities().stream()
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
}
