package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.ScheduleEntry;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.*;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="SCHEDULE")
public class ScheduleTest {
    private HashMap<LocalDate, ScheduleEntryTest> scheduleDayMap;
    private Person person;
    private String personString;

    public ScheduleTest(String personString) {
        this.personString = personString;
        scheduleDayMap = new HashMap<>();
    }

    public HashMap<LocalDate, ScheduleEntryTest> getScheduleEntryTestList() {
        return scheduleDayMap;
    }

    /**
     * Adds a scheduleEntry into the scheduleEntryTestList. Returns false if there already is a scheduleEntry for the chosen date, otherwise returns true
     * @param date
     * @return boolean
     */
    public boolean addScheduleEntry(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            return false;
        } else {
            scheduleDayMap.put(date, new ScheduleEntryTest(date));
            return true;
        }
    }

    public HashMap<LocalDate, ScheduleEntryTest> getScheduleDayMap() {
        return scheduleDayMap;
    }

    public ScheduleEntryTest getEntryForDay(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            return scheduleDayMap.get(date);
        } else {
            return null;
        }
    }

    public boolean deleteScheduleEntryDay(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            scheduleDayMap.remove(date);
            return true;
        } else {
            return false;
        }
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
