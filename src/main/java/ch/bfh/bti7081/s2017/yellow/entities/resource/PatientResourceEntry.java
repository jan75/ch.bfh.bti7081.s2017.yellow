package ch.bfh.bti7081.s2017.yellow.entities.resource;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;

import java.util.List;

/**
 * Created by samuel on 15.05.17.
 */
public class PatientResourceEntry implements Storable {

    private Long id;

    private int totalHour;

    private Patient patient;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(int totalHour){
        this.totalHour = totalHour;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
