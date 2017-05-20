package ch.bfh.bti7081.s2017.yellow.beans;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by simon on 16.05.17.
 */
public class ContactBookEntryBean<E> extends BaseBean<E> {

    @NotEmpty
    private String firstName = "";
    @NotEmpty
    private String lastName = "";
    @NotBlank
    private String phoneNr = "";

    public ContactBookEntryBean() {
    }

    public ContactBookEntryBean(String firstName, String lastName, String phoneNr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNr = phoneNr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}
