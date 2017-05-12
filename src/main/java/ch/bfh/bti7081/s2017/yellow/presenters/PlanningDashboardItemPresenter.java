package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.DashboardItemView;
import ch.bfh.bti7081.s2017.yellow.views.PlanningDashboardItemView;

/**
 * Created by taahuem2 on 08.05.17.
 */
public class PlanningDashboardItemPresenter implements DashboardItemPresenter {

    PlanningDashboardItemView planningDashboardItemView;

    public PlanningDashboardItemPresenter(PlanningDashboardItemView view) {
        this.planningDashboardItemView = view;
    }

    @Override
    public DashboardItemView getView() {
        return this.planningDashboardItemView;
    }
}
