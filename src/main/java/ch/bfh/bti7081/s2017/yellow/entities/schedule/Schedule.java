package ch.bfh.bti7081.s2017.yellow.entities.schedule;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * This class is used as an entity for the schedule to be able to store it in the database
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

    public HashMap<LocalDate, HashMap<Integer, String>> getScheduleDayMap() {
        return scheduleDayMap;
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
