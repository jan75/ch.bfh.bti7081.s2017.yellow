package ch.bfh.bti7081.s2017.yellow.beans.schedule;

import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;

/**
 * Created by samuel on 26.05.17.
 */
public class EstimationViewBean {

    private DailyEstimationBean dailyEstimationBean;

    private PatientBean patientBean;

    private PatientEstimationBean newPatientEstimation;

    public DailyEstimationBean getDailyEstimationBean() {
        return dailyEstimationBean;
    }

    public void setDailyEstimationBean(DailyEstimationBean dailyEstimationBean) {
        this.dailyEstimationBean = dailyEstimationBean;
    }

    public PatientBean getPatientBean() {
        return patientBean;
    }

    public void setPatientBean(PatientBean patientBean) {
        this.patientBean = patientBean;
    }

    public PatientEstimationBean getNewPatientEstimation() {
        if (newPatientEstimation == null) {
            newPatientEstimation = new PatientEstimationBean();
        }

        return newPatientEstimation;
    }

    public void setNewPatientEstimation(PatientEstimationBean newPatientEstimation) {
        this.newPatientEstimation = newPatientEstimation;
    }
}
