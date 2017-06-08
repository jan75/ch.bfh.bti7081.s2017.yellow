package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeePlanningBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningDetailView;

import java.time.LocalDate;

public class PlanningDetailPresenter {
    private PlanningDetailView view;

    public PlanningDetailPresenter(PlanningDetailView view) {

        // concrete ContactView
        this.view = view;

        NavigatorController.getInstance().addView("planningDetailView", view);
    }

    /**
     * @return The instance of the presenters view
     */
    public PlanningDetailView getView() {
        return view;
    }

    public void updateView(EmployeePlanningBean employee, LocalDate date) {
        view.updateView(employee, date);
    }
}