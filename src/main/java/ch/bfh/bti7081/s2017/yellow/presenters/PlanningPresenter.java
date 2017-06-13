package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.services.PlanningService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningView;

/**
 * The presenter which supplies the planning view with logic
 */
public class PlanningPresenter {
    private PlanningView view;
    PlanningService planningService = new PlanningService();

    /**
     * Creates a new PlanningPresenter instance which knows its view
     * @param view the view to link this presenter to
     */
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

    /**
     * Adds an employee to the database, then updates the view accordingly
     * @param firstName the first name of the to be added employee
     * @param lastName the last name of the to be added employee
     */
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

    /**
     * Loads the employees in the view
     */
    public void loadEmployees() {
        view.loadEmployees(planningService.getEmployees());
    }
}