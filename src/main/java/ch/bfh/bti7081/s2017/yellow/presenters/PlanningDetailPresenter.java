package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeeBean;
import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.beans.ScheduleBean;
import ch.bfh.bti7081.s2017.yellow.services.PlanningService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningDetailView;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * This class supplies the logic to the PlanningDetailView
 */
public class PlanningDetailPresenter {
    private PlanningDetailView view;
    private PlanningService planningService = new PlanningService();

    public PlanningDetailPresenter(PlanningDetailView view) {
        this.view = view;

        NavigatorController.getInstance().addView("planningDetailView", view);
    }

    public PlanningDetailView getView() {
        return view;
    }

    /**
     * This class updates the view with the currently to show employee with the according schedule
     * @param employee the employee which to display
     * @param date the date for which the schedule is to display
     */
    public void updateView(EmployeePlanningBean employee, LocalDate date) {
        view.updateView(employee, date);
    }

    /**
     * This method adds a planning entry (activity for a timespan) into the schedule for the chosen day
     * @param beginningTime Time when entry begins
     * @param endingTime Time when entry ends
     * @param activity Activity for the time
     * @param employee The employee for which to update the schedule
     * @param date The date for which the schedule is to be adjusted
     */
    public void addPlanningEntry(Integer beginningTime, Integer endingTime, String activity, EmployeePlanningBean employee, LocalDate date) {
        if(beginningTime <= endingTime && beginningTime != null && endingTime != null && activity != null && activity != "") {
            ScheduleBean schedule = employee.getSchedule();
            HashMap<Integer, String> scheduleDay = employee.getSchedule().getEntryForDay(date);
            for(int i = beginningTime.intValue(); i < endingTime; i++) {
                scheduleDay.put(i, activity);
            }
            schedule.setScheduleForDay(date, scheduleDay);
            employee.setSchedule(schedule);
            planningService.saveEntity(employee);

            this.updateView(employee, date);
        }
    }
}