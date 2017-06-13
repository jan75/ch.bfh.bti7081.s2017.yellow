package ch.bfh.bti7081.s2017.yellow.beans.schedule;

import ch.bfh.bti7081.s2017.yellow.beans.BaseBean;
import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;
import ch.bfh.bti7081.s2017.yellow.entities.schedule.PatientEstimation;

/**
 * Created by samuel on 24.05.17.
 */
public class PatientEstimationBean extends BaseBean<PatientEstimation> {

    private int totalHour;

    private PatientBean patient;

    public PatientEstimationBean() {}

    public int getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(int totalHour) {
        this.totalHour = totalHour;
    }

    public PatientBean getPatient() {
        return patient;
    }

    public void setPatient(PatientBean patient) {
        this.patient = patient;
    }
}
