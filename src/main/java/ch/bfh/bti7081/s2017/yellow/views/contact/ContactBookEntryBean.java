package ch.bfh.bti7081.s2017.yellow.views.contact;

/**
 * Created by simon on 16.05.17.
 */
public class ContactBookEntryBean {

    private String firstName, lastName, phoneNumber;

    public ContactBookEntryBean() {
    }

    public ContactBookEntryBean(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
