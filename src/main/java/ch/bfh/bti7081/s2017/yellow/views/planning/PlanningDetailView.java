package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import com.vaadin.ui.Component;

import java.time.LocalDate;

/**
 * Interface for a PlanningViewImpl class. Supports public methods for the Presenter
 * @author Jan Ackermann
 */
public interface PlanningDetailView extends Component, NavigatorView  {
    /**
     * Method to be implemented to update the view from the presenter
     * @param employee The employee which is to display
     * @param date The date for which the schedule is to be displayed
     */
    void updateView(EmployeePlanningBean employee, LocalDate date);
}