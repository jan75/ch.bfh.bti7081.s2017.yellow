package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.ScheduleEntry;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="SCHEDULE")
public class ScheduleTest {
    private List<ScheduleEntryTest> scheduleEntryTestList;
    private Person person;
    private String personString;

    public ScheduleTest(String personString) {
        this.personString = personString;
        scheduleEntryTestList = new ArrayList<>();
    }

    public List<ScheduleEntryTest> getScheduleEntryTestList() {
        return scheduleEntryTestList;
    }

    public void setScheduleEntryTestList(List<ScheduleEntryTest> scheduleEntryTestList) {
        this.scheduleEntryTestList = scheduleEntryTestList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPersonString() {
        return personString;
    }

    public void setPersonString(String personString) {
        this.personString = personString;
    }
}
