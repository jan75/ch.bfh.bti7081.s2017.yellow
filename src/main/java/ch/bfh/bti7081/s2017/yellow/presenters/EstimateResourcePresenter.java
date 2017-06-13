package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.schedule.EstimationViewBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.PatientEstimationBean;
import ch.bfh.bti7081.s2017.yellow.services.EstimationService;
import ch.bfh.bti7081.s2017.yellow.views.schedule.PatientEstimationView;
import ch.bfh.bti7081.s2017.yellow.views.listeners.EstimateResourceListener;

import java.time.LocalDate;

/**
 * Created by samuel on 16.05.17.
 */
public class EstimateResourcePresenter implements EstimateResourceListener {

    private EstimationService service;

    private PatientEstimationView view;

    private EstimationViewBean bean;

    public EstimateResourcePresenter(PatientEstimationView view) {
        this(view, new EstimationService());
    }

    public EstimateResourcePresenter(PatientEstimationView view, EstimationService service) {
        this.view = view;
        this.service = service;

        bean = new EstimationViewBean();
        changeDate(LocalDate.now());
        view.addListener(this);
    }

    @Override
    public void changeDate(LocalDate date) {
        bean.setDailyEstimationBean(service.getDailyEstimation(date));
        bean.setPatientBean(service.getPatientWhereNotAdded());
        view.setPatientEstimation(bean);
    }

    @Override
    public void addEstimation() {
        service.addPatientEstimation(bean.getNewPatientEstimation());
        bean.setPatientBean(service.getPatientWhereNotAdded());
        bean.setNewPatientEstimation(new PatientEstimationBean());
        view.setPatientEstimation(bean);
    }

    @Override
    public void saveClicked() {
        service.saveDay();
    }
}
