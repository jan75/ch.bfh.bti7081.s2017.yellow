package ch.bfh.bti7081.s2017.yellow.entities.resource;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import java.util.Date;
import java.util.List;
import java.util.logging.LoggingMXBean;

/**
 * Created by samuel on 15.05.17.
 */
public class WeekResource implements Storable {

    private Long id;

    private List<PatientResourceEntry> patientResources;


    private int weekOfYear;

    private int year;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<PatientResourceEntry> getPatientResources() {
        return patientResources;
    }

    public void setPatientResources(List<PatientResourceEntry> patientResources) {
        this.patientResources = patientResources;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(int weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
