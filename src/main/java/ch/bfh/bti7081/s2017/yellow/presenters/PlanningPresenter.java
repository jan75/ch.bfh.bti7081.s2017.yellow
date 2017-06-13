package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningView;

public class PlanningPresenter {
    private PlanningView view;

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
}