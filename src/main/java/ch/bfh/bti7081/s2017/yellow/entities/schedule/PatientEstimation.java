package ch.bfh.bti7081.s2017.yellow.entities.schedule;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.Patient;

import javax.persistence.*;
import java.util.List;

/**
 * Created by samuel on 15.05.17.
 */
@Entity
@Table(name="PATIENT_ESTIMATION")
public class PatientEstimation implements Storable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name = "TOTAL_HOUR")
    private int totalHour;

    @OneToOne(fetch = FetchType.LAZY)
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
