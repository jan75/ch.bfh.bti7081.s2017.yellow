package ch.bfh.bti7081.s2017.yellow.beans.schedule;

import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.DailyEstimation;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by samuel on 24.05.17.
 */
public class DailyEstimationBean extends BaseBean<DailyEstimation> {

    private List<PatientEstimationBean> patientEstimation;

    private LocalDate date;

    public DailyEstimationBean() {}

    public List<PatientEstimationBean> getPatientEstimation() {
        return patientEstimation;
    }

    public void setPatientEstimation(List<PatientEstimationBean> patientEstimation) {
        this.patientEstimation = patientEstimation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
