package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.entities.person.Employee;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactViewImpl;
import com.vaadin.ui.Component;
import org.joda.time.LocalDate;

/**
 * Interface for a ContactViewImpl class. Supports public methods for the Presenter.
 * @author iSorp
 * @see ContactViewImpl
 */
public interface PlanningDetailView extends Component, NavigatorView  {
    void updateView(Employee employee, LocalDate date);
}
