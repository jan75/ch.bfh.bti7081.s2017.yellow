package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by simon on 16.05.17.
 */
public class ContactBookEntryBean extends BaseBean<ContactBookEntry> {

    @NotBlank
    private String phoneNr = "";
    @NotBlank
    private String firstName = "";
    @NotBlank
    private String lastName = "";

    @NotNull
    private PersonBean person = new PersonBean();

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

    public String getFirstName() {
        return person.getFirstName();
    }

    public void setFirstName(String firstName) {
        this.person.setFirstName(firstName);
        this.firstName = firstName;
    }
    public String getLastName() {
        return person.getLastName();
    }

    public void setLastName(String lastName) {
        this.person.setLastName(lastName);
        this.lastName = lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }
    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}
