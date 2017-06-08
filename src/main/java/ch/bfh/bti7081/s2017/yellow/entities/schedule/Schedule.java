package ch.bfh.bti7081.s2017.yellow.entities.schedule;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import java.time.LocalDate;

import javax.persistence.*;
import java.util.HashMap;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="SCHEDULE")
public class Schedule implements Storable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="SCHEDULE_LIST")
    @Lob
    private HashMap<LocalDate, HashMap<Integer, String>> scheduleDayMap;

    public Schedule () {
        scheduleDayMap = new HashMap<>();
    }

    public HashMap<LocalDate, HashMap<Integer, String>> getScheduleEntryTestList() {
        return scheduleDayMap;
    }

    /**
     * Adds a schedule for a day into the scheduleDayMap. Returns false if there already is a Schedule for the chosen date, otherwise returns true
     * @param date
     * @return boolean
     */
    public boolean addScheduleEntry(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            return false;
        } else {
            scheduleDayMap.put(date, initializeDaySchedule());
            return true;
        }
    }

    private HashMap<Integer, String> initializeDaySchedule() {
        HashMap<Integer, String> dayMap = new HashMap<>();
        for(int i = 0; i <= 23; i++) {
            dayMap.put(i, null);
        }
        return dayMap;
    }

    public HashMap<LocalDate, HashMap<Integer, String>> getScheduleDayMap() {
        return scheduleDayMap;
    }

    public HashMap<Integer, String> getEntryForDay(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            return scheduleDayMap.get(date);
        } else {
            return null;
        }
    }

    public void setScheduleForDay(LocalDate date, HashMap<Integer, String> scheduleDay) {
        scheduleDayMap.put(date, scheduleDay);
    }

    public boolean deleteScheduleEntryDay(LocalDate date) {
        if(scheduleDayMap.containsKey(date)) {
            scheduleDayMap.remove(date);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


}
