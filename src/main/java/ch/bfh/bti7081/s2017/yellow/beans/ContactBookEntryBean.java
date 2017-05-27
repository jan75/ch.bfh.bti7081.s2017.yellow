package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 16.05.17.
 */
public class ContactBookEntryBean<TBean extends PersonBean> extends BaseBean<ContactBookEntry> {

    private Class<TBean> clazz;
    private ContactEntryType contactEntryType = ContactEntryType.Person;
    @NotBlank
    private String phoneNr = "";
    @NotNull
    private TBean person;

    public Class getType(){
        return person.getClass();
    }

    public ContactBookEntryBean() { }

    public ContactBookEntryBean(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public TBean getPerson(){
        return this.person;
    }

    public void setPerson(TBean person){
        this.person = person;
    }
    
    public String getPhoneNr() {
        return phoneNr;
    }
    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}
