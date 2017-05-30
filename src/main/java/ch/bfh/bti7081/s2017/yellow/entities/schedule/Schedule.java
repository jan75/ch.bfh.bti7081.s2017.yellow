package ch.bfh.bti7081.s2017.yellow.entities.schedule;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import java.util.List;

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

    @OneToMany
    private List<ScheduleEntry> scheduleEntry;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

	public List<ScheduleEntry> getScheduleEntry() {
		return scheduleEntry;
	}

	public void setScheduleEntry(List<ScheduleEntry> scheduleEntry) {
		this.scheduleEntry = scheduleEntry;
	}
}
