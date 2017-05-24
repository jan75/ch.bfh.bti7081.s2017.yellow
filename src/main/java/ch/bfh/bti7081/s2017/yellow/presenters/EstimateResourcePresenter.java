package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.services.ResourceService;
import ch.bfh.bti7081.s2017.yellow.views.EstimateResourceView;
import ch.bfh.bti7081.s2017.yellow.views.listeners.EstimateResourceListener;

import java.time.LocalDate;

/**
 * Created by samuel on 16.05.17.
 */
public class EstimateResourcePresenter implements EstimateResourceListener {

    private ResourceService service;

    private EstimateResourceView view;

    public EstimateResourcePresenter(EstimateResourceView view) {
        this(view, new ResourceService());
    }

    public EstimateResourcePresenter(EstimateResourceView view, ResourceService service) {
        this.view = view;
        this.service = service;

        view.addListener(this);
        view.setPatientResources(service.getPatientResource());
    }

    @Override
    public void changeDate(LocalDate date) {
        service.loadWeek(date);
        view.setPatientResources(service.getPatientResource());
    }
}
