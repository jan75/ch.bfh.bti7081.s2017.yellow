package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.ContactDashboardItemView;

/**
 * Created by jan on 5/8/17.
 */
public class ContactDashboardPresenter implements DashboardItemPresenter {

    private ContactDashboardItemView view = null;

    /**
     * Default DashboardPresenter Constructor.
     * @param view
     */
    public ContactDashboardPresenter(ContactDashboardItemView view) {
        this.view = view;
    }

    /**
     * @return The instance of the presenters view
     */
    public ContactDashboardItemView getView() {
        return view;
    }
}
