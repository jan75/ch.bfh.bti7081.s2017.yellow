package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.presenters.DashboardPresenter;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;

/**
 * Container for DashboardItems, Vertically layouted.
 * @author iSorp
 */
public class DashboardContactViewImpl extends CustomComponent implements DashboardContactView {

    public DashboardContactViewImpl() {
        setCompositionRoot(new Button("dashboard contact view impl"));
    }
}
