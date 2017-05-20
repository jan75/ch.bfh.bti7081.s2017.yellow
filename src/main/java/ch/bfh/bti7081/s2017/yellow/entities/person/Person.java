package ch.bfh.bti7081.s2017.yellow.entities.person;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import javax.persistence.*;

@Entity
@Table(name="PERSON")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person implements Storable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="FIRST_NAME")
    private String firstName = "";

    @Column(name = "LAST_NAME")
    private String lastName = "";

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
}
