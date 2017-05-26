package ch.bfh.bti7081.s2017.yellow.entities.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee extends Person {

    @Column(name="SINCE")
    private Date since;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }
}
