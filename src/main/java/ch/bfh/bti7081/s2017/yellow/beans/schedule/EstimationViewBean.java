package ch.bfh.bti7081.s2017.yellow.beans.schedule;

import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;

import java.util.List;

/**
 * Created by samuel on 26.05.17.
 */
public class EstimationViewBean {

    private DailyEstimationBean dailyEstimationBean;

    private List<PatientBean> patientsBean;

    private PatientEstimationBean newPatientEstimation;

    public DailyEstimationBean getDailyEstimationBean() {
        return dailyEstimationBean;
    }

    public void setDailyEstimationBean(DailyEstimationBean dailyEstimationBean) {
        this.dailyEstimationBean = dailyEstimationBean;
    }

    public List<PatientBean> getPatientBean() {
        return patientsBean;
    }

    public void setPatientBean(List<PatientBean> patientsBean) {
        this.patientsBean = patientsBean;
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
