package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.DashboardItem;
import ch.bfh.bti7081.s2017.yellow.views.PlanningDashboardItemView;

/**
 * Created by taahuem2 on 08.05.17.
 */
public class PlanningDashboardItemPresenter implements DashboardItemPresenter {

    private DashboardItem view;

    public PlanningDashboardItemPresenter(PlanningDashboardItemView view) {
        this.view = view;
    }

    @Override
    public DashboardItem getView() {
        return this.view;
    }
}
