package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.*;

import java.util.ArrayList;

/**
 * Presenter for a DashboarView. Supports adding and visualizing of DashboardItems
 * @author iSorp
 */
public class DashboardPresenter {

    private DashboardView view = null;
    private static ArrayList<DashboardItemPresenter> dashboardItemList = new ArrayList<DashboardItemPresenter>();

    /**
     * Default DashboardPresenter Constructor.
     * @param view
     */
    public DashboardPresenter(DashboardView view) {
        this.view = view;
        ContactDashboardItemView contactDashboardView = new ContactDashboardItemViewImpl();
        addDashboardItem(new ContactDashboardItemPresenter(contactDashboardView));
        WikiDashboardItemView wikiDashboardItemView = new WikiDashboardItemViewImpl();
        addDashboardItem(new WikiDashboardItemPresenter(wikiDashboardItemView));
        PlanningDashboardItemView planningDashboardItemView = new PlanningDashboardItemViewImpl();
        addDashboardItem(new PlanningDashboardItemPresenter(planningDashboardItemView));
    }

    /**
     * TODO: We need a DashboardItemPresenter class or interface
     * TODO: DashboardItemPresenter must provide at least the method getView()
     * Adds a new DashboarItemPresenter to the Dashboard.
     * @param item A instance of an DashboardItemPresenter
     * @throws -
     * @author iSorp
     */
    public void addDashboardItem(DashboardItemPresenter item) {

        dashboardItemList.add(item);
        this.view.addComponent(item.getView());
    }

    /**
     * @return The instance of the presenters view
     */
    public DashboardView getView() {
        return view;
    }
}
