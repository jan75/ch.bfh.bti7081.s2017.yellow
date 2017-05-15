package ch.bfh.bti7081.s2017.yellow.entities.person;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="NATURAL_PERSON")
public class NaturalPerson extends Person{

    public NaturalPerson(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
