package ch.bfh.bti7081.s2017.yellow.entities.person;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity()
@Table(name="PERSON")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DynamicUpdate
public class Person implements Storable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="FIRST_NAME")
    private String firstName = "";

    @Column(name = "LAST_NAME")
    private String lastName = "";
    
    @OneToMany(cascade= {CascadeType.PERSIST}, mappedBy="person")
    private List<ContactBookEntry> contactBookEntries;

    public Person() {
    	
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public List<ContactBookEntry> getContactBookEntries() {
		return contactBookEntries;
	}

	public void setContactBookEntries(List<ContactBookEntry> contactBookEntries) {
		this.contactBookEntries = contactBookEntries;
	}
    
    
}
