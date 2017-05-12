package ch.bfh.bti7081.s2017.yellow.views;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Container for DashboardItems, Vertically layouted.
 * @author iSorp
 */
public class ContactDashboardItemViewImpl extends HorizontalLayout implements ContactDashboardItemView {

    public ContactDashboardItemViewImpl() {
        /*
        HorizontalLayout menuLayout = new HorizontalLayout();
        menuLayout.addComponent(new Button("dashboard contact view impl"));
        setCompositionRoot(menuLayout);
        */
        Label label = new Label("Dashboard Contact View Impl");
        addComponent(label);
    }
}
