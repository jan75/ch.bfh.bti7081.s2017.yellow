package ch.bfh.bti7081.s2017.yellow.views;

import com.vaadin.ui.VerticalLayout;

/**
 * Container for DashboardItems, Vertically layouted.
 * @author iSorp
 */
public class DashboardViewImpl extends VerticalLayout implements DashboardView {

    public DashboardViewImpl() {}

    @Override
    public void addComponent(DashboardItemView dashboardViewItem) {
        super.addComponent(dashboardViewItem);
    }


    /*
    * TODO: Maybe some listeners are needed?
    * */
}
