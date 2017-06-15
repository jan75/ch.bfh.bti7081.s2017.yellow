package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.*;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

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

        //Accordion
        Accordion accordion = new Accordion();
        accordion.setHeight("500");
        accordion.setWidth("1124");

        //add Wiki to accordion
        Layout wikiDashboardItemViewTab = new VerticalLayout();
        wikiDashboardItemViewTab.addComponent(wikiDashboardItemView);
        accordion.addTab(wikiDashboardItemViewTab, "Wiki Dashboard");

        //add Contacts to accordion
        Layout contactDashboardItemViewTab = new VerticalLayout();
        contactDashboardItemViewTab.addComponent(contactDashboardView);
        accordion.addTab(contactDashboardItemViewTab, "Contact Dashboard");

        //add Planning to accordion
        Layout planningDashboardItemViewTab = new VerticalLayout();
        planningDashboardItemViewTab.addComponent(planningDashboardItemView);
        accordion.addTab(planningDashboardItemViewTab, "Planning Dashboard");

        this.view.addComponent(accordion);
    }

    /**
     * TODO: We need a DashboardItemPresenter class or interface
     * TODO: DashboardItemPresenter must provide at least the method getView()
     * Adds a new DashboarItemPresenter to the Dashboard.
     * @param item A instance of an DashboardItemPresenter
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
