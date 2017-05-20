package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapper;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import com.vaadin.data.provider.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by simon on 15.05.17.
 */
public class ContactService extends SimpleServiceImpl<ContactBook> {

    public List<ContactBookEntryBean> contactList = new ArrayList<>();
    private BeanMapper<ContactBookEntry, ContactBookEntryBean> beanMapper = new BeanMapper<>();
    private String filter;

    public ContactService(){

        List<ContactBook> mockList = new ArrayList<>();
        ContactBook contactBook = new ContactBook();
        contactBook.setEntries(new ContactBookEntry(new Person("simon", "wälti"), "0797492467"));
        contactBook.setEntries(new ContactBookEntry(new Person("simon", "wälti"), "0797492467"));
        contactBook.setEntries(new ContactBookEntry(new Person("hugo", "habicht"), "0797492467"));
        mockList.add(contactBook);

        mockList.forEach(book -> book.getEntries().forEach(entry ->
            contactList.add(beanMapper.getEntityBean(entry, new ContactBookEntryBean()))
        ));
    }

    /**
     *
     * @param contactBookEntryBean
     */
    public void saveEntities(ContactBookEntryBean contactBookEntryBean) {
        super.saveEntities(null);
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
            return a.getFirstName().contains(filter) || a.getLastName().contains(filter);
            };
        // Mapping of a ContactBook list to a list of ContactBookEntry list
        return contactList.stream()
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
            query -> getContactBookEntries().size()
    );
}
