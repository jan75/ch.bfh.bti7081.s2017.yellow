package ch.bfh.bti7081.s2017.yellow.entities.person;

import ch.bfh.bti7081.s2017.yellow.entities.schedule.Schedule;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee extends Person {
    @Column(name="SINCE")
    private Date since;

    @OneToOne(cascade=CascadeType.ALL)
    private Schedule schedule;

    public Employee() {
    }


    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
        schedule = new Schedule();
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
