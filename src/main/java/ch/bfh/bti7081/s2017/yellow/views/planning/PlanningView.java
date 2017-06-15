package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * Interface for a PlanningViewImpl class. Supports public methods for the Presenter.
 * @author Jan Ackermann
 */
public interface PlanningView extends Component, NavigatorView  {
    /**
     * This method loads the employees
     * @param employeePlanningBeanList The list of employees to load
     */
     void loadEmployees(List<EmployeePlanningBean> employeePlanningBeanList);
}
