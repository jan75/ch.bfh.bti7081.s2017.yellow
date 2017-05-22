package ch.bfh.bti7081.s2017.yellow.services;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookBean;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapper;
import ch.bfh.bti7081.s2017.yellow.util.BeanMapperImpl;
import com.vaadin.data.provider.DataProvider;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.Criteria;

import java.util.ArrayList;
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
            ContactBookEntryBean entryBean = new ContactBookEntryBean();
            entryBean.setFirstName("Hugo");
            entryBean.setLastName("Habicht");
            entryBean.setPhoneNr("999999");
            contactBookBean.addEntry(entryBean);
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

    @Override
    public void mapEntityToBean(ContactBook contactEntity, ContactBookBean contactBookBean) {
        // Add all entity references to the beans
        /*contactBookBean.setEntity(contactEntity);
        for (int i = 0; i < contactBookBean.getEntries().size(); i++) {
            contactBookBean.getEntries().get(i).setEntity(contactEntity.getEntries().get(i));
        }*/
    }
}
