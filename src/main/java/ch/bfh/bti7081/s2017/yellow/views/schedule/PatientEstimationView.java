package ch.bfh.bti7081.s2017.yellow.views.schedule;

import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.EstimationViewBean;
import ch.bfh.bti7081.s2017.yellow.beans.schedule.PatientEstimationBean;
import ch.bfh.bti7081.s2017.yellow.views.listeners.EstimateResourceListener;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * Created by samuel on 16.05.17.
 */
public interface PatientEstimationView extends Component, View {

    void setPatientEstimation(EstimationViewBean estimation);

    void addListener(EstimateResourceListener listener);
}
