package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.entities.resource.PatientResourceEntry;
import ch.bfh.bti7081.s2017.yellow.views.listeners.EstimateResourceListener;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * Created by samuel on 16.05.17.
 */
public interface EstimateResourceView extends Component {

    void setPatientResources(List<PatientResourceEntry> resources);

    void addListener(EstimateResourceListener listener);
}
