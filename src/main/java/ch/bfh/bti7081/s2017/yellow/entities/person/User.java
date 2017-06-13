package ch.bfh.bti7081.s2017.yellow.entities.person;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;

import javax.persistence.*;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="USER")
public class User extends Person {

    public User() {

    }

    @OneToOne
    private ContactBook contactBook;

    public User(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public ContactBook getContactBook() {
        return contactBook;
    }

    public void setContactBook(ContactBook contactBook) {
        this.contactBook = contactBook;
    }
}
