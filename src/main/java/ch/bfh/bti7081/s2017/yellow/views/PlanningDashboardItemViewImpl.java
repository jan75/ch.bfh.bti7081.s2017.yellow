package ch.bfh.bti7081.s2017.yellow.views;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Created by taahuem2 on 08.05.17.
 */
public class PlanningDashboardItemViewImpl extends HorizontalLayout implements PlanningDashboardItemView {

    public PlanningDashboardItemViewImpl() {
        Label label = new Label("Dashboard Planning View Impl");
        addComponent(label);
    }

}
