package ch.bfh.bti7081.s2017.yellow.views.planning;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactViewImpl;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * Interface for a ContactViewImpl class. Supports public methods for the Presenter.
 * @author iSorp
 * @see ContactViewImpl
 */
public interface PlanningView extends Component, NavigatorView  {
    void addEmployee(String firstName, String lastName);

    void loadEmployees(List<EmployeePlanningBean> employeePlanningBeanList);
}
