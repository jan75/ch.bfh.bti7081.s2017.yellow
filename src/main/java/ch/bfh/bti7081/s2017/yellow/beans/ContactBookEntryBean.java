package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ma.glasnost.orika.metadata.NestedProperty;
import ma.glasnost.orika.metadata.Property;
import ma.glasnost.orika.property.PropertyResolverStrategy;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ma.glasnost.orika.*;

/**
 * Created by simon on 16.05.17.
 */
public class ContactBookEntryBean extends BaseBean<ContactBookEntry> implements PropertyResolverStrategy {

    private ContactEntryType contactEntryType = ContactEntryType.Person;
    @NotBlank
    private String phoneNr = "";
    @NotNull
    private PersonBean person;
    @NotNull
    private ContactBookBean contactBook;

    public Class getType(){
        return person.getClass();
    }

    public ContactBookEntryBean() { }

    public ContactBookEntryBean(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public PersonBean getPerson(){
        return this.person;
    }

    public void setPerson(PersonBean person){
        this.person = person;
    }
    
    public String getPhoneNr() {
        return phoneNr;
    }
    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public void setContactBook(ContactBookBean contactBook) {
        this.contactBook = contactBook;
    }
    public ContactBookBean getContactBook(){
        return contactBook;
    }

    @Override
    public Map<String, Property> getProperties(Type type) {
        return null;
    }

    @Override
    public NestedProperty getNestedProperty(Type type, String s) {
        return null;
    }

    @Override
    public Property getProperty(Type type, String s) {
        return null;
    }

    @Override
    public boolean existsProperty(Type type, String s) {
        return false;
    }

    @Override
    public Property getProperty(Property property, String s) {
        return null;
    }
}
