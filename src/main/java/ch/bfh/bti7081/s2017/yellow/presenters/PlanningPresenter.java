package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.services.PlanningService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningDetailView;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningDetailViewImpl;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningView;

import java.time.LocalDate;

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
     * This methods adds a schedule for the date defined in the calendar input field and navigates to it
     * @param employeePlanningBean The employee who gets the new schedule entry into his schedule
     * @param date The date for which to add the schedule entry
     * @param planningDetailPresenter The instance of the presenter for the detail view
     */
    public void addSchedule(EmployeePlanningBean employeePlanningBean, LocalDate date, PlanningDetailPresenter planningDetailPresenter) {
        if(date != null) {
            //String dateString = date.toString();
            //LocalDate dateTime = LocalDate.parse(dateString);
            //System.out.println(dateTime);
            ScheduleBean scheduleBean = employeePlanningBean.getSchedule();
            scheduleBean.addScheduleEntry(date);
            employeePlanningBean.setSchedule(scheduleBean);
            planningService.saveEntity(employeePlanningBean);

            planningDetailPresenter.updateView(employeePlanningBean, date);
            NavigatorController.getInstance().navigateTo("planningDetailView");
        }
    }

    /**
     * Loads the employees in the view
     */
    public void loadEmployees() {
        view.loadEmployees(planningService.getEmployees());
    }
}