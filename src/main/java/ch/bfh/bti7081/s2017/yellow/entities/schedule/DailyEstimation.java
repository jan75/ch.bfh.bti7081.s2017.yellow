package ch.bfh.bti7081.s2017.yellow.entities.schedule;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 15.05.17.
 */
@Entity
@Table(name="DAILY_ESTIMATION")
public class DailyEstimation implements Storable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PatientEstimation> patientEstimation;

    @Column(name="DATE")
    private LocalDate date;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<PatientEstimation> getPatientEstimation() {
        return patientEstimation;
    }

    public void setPatientEstimation(List<PatientEstimation> patientEstimation) {
        this.patientEstimation = patientEstimation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
