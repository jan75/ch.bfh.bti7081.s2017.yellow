package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningDetailView;

import java.time.LocalDate;

/**
 * This class supplies the logic to the PlanningDetailView
 */
public class PlanningDetailPresenter {
    private PlanningDetailView view;

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
}