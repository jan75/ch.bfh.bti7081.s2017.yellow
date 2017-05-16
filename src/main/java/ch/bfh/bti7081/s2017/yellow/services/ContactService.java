package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by simon on 15.05.17.
 */
public class ContactService extends SimpleServiceImpl<ContactBook> {

    private String filter;
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
        return getALlEntities().stream()
                .flatMap(a -> a.getEntries().stream())
                .filter(firstNamePredicate)
                .collect(Collectors.toList());
    }

}
