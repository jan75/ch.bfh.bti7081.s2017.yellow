package ch.bfh.bti7081.s2017.yellow.entities.schedule;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import javax.persistence.*;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="SCHEDULE")
public class Schedule implements Storable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="SCHEDULE_ENTRY")
    private ScheduleEntry scheduleEntry;

    public Schedule(ScheduleEntry scheduleEntry) {
        this.scheduleEntry = scheduleEntry;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ScheduleEntry getScheduleEntry() {
        return scheduleEntry;
    }

    public void setScheduleEntry(ScheduleEntry scheduleEntry) {
        this.scheduleEntry = scheduleEntry;
    }
}
