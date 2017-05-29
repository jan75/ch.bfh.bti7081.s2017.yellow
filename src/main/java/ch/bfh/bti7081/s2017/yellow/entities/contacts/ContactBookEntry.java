package ch.bfh.bti7081.s2017.yellow.entities.contacts;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="CONTACT_BOOK_ENTRY")
public class ContactBookEntry implements Storable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @OneToOne()
    @Cascade(CascadeType.ALL)
    private Person person;

    @Column(name="PHONE_NR")
    private String phoneNr;

    public ContactBookEntry(){ }

    public ContactBookEntry(Person person, String phoneNr) {
        this.person = person;
        this.phoneNr = phoneNr;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson(){
        return this.person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public String getPhoneNr (){
        return this.phoneNr;
    }

    public void setPhoneNr(String phoneNr){
        this.phoneNr = phoneNr;
    }
}
