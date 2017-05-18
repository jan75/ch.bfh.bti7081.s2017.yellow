package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactBookEntryBean;
import com.vaadin.data.provider.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by simon on 15.05.17.
 */
public class ContactService extends SimpleServiceImpl<ContactBook> {

    private String filter;
    public List<ContactBook> contactList = new ArrayList<>();

    public ContactService(){

        ContactBook contactBook = new ContactBook();
        contactBook.setEntries(new ContactBookEntry(new Person("simon", "wälti"), "0797492467"));
        contactBook.setEntries(new ContactBookEntry(new Person("simon", "wälti"), "0797492467"));
        contactBook.setEntries(new ContactBookEntry(new Person("hugo", "habicht"), "0797492467"));
        contactList.add(contactBook);
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
    public List<ContactBookEntry> getContactBookEntries(){

        // Predicate for filter
        Predicate<ContactBookEntry> firstNamePredicate = a -> {
            if (filter == null || filter == "")
                return true;
            return a.getPerson().getFirstName().contains(filter) || a.getPerson().getLastName().contains(filter);
            };
        // Mapping of a ContactBook list to a list of ContactBookEntry list
        return contactList.stream()
                .flatMap(a -> a.getEntries().stream())
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

    //ConfigurableFilterDataProvider<GridContactBookEntryBean, Void, String> wrapper =  getContactBookDataProvider().withConfigurableFilter();
    DataProvider<ContactBookEntryBean, String> dataProvider = DataProvider.fromFilteringCallbacks(
            // First callback fetches items based on a query
            query -> { List<ContactBookEntryBean> entryList = new ArrayList<>();
                for (ContactBookEntry entry : this.getContactBookEntries()) {
                    entryList.add(new ContactBookEntryBean(
                            entry.getPerson().getFirstName(),
                            entry.getPerson().getLastName(),
                            entry.getPhoneNr()));
                }
                return entryList.stream();
            },
            // Second callback fetches the number of items for a query
            query -> getContactBookEntries().size()
    );
}
