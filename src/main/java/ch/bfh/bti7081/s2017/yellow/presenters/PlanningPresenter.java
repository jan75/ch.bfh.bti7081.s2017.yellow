package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeeBean;
import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.services.PlanningService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningView;
import com.vaadin.ui.Component;

import java.util.List;

public class PlanningPresenter {
    private PlanningView view;
    PlanningService planningService = new PlanningService();

    public PlanningPresenter(PlanningView view) {

        // concrete ContactView
        this.view = view;

        NavigatorController.getInstance().addView("planningView", view);
    }

    /**
     * @return The instance of the presenters view
     */
    public PlanningView getView() {
        return view;
    }

    public void addEmployee(String firstName, String lastName) {
        if(firstName != "" && lastName != "") {
            EmployeePlanningBean employeePlanningBean = new EmployeePlanningBean();
            employeePlanningBean.setFirstName(firstName);
            employeePlanningBean.setLastName(lastName);
            ScheduleBean scheduleBean = new ScheduleBean();
            employeePlanningBean.setSchedule(scheduleBean);
            planningService.saveEntity(employeePlanningBean);

            loadEmployees();
        }
    }

    public void loadEmployees() {
        view.loadEmployees(planningService.getEmployees());
    }
}