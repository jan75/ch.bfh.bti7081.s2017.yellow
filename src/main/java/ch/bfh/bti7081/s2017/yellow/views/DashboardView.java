package ch.bfh.bti7081.s2017.yellow.views;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.Component;

/**
 * Interface for a DashboardViewImpl class. Supports public methods for the Presenter.
 * @author iSorp
 * @see DashboardViewImpl
 */
public interface DashboardView extends Component {
    /**
     * Adds a DashboardViewItem component to a View.
     * @param dashboardViewItem
     */
    void addComponent(DashboardItemView dashboardViewItem);

    void addComponent(Accordion accordion);
}
