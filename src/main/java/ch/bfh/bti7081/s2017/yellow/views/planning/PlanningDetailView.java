package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactViewImpl;
import com.vaadin.ui.Component;
import java.time.LocalDate;

/**
 * Interface for a ContactViewImpl class. Supports public methods for the Presenter.
 * @author iSorp
 * @see ContactViewImpl
 */
public interface PlanningDetailView extends Component, NavigatorView  {
    void updateView(EmployeePlanningBean employee, LocalDate date);
}
