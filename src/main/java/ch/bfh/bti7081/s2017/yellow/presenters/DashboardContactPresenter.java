package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.DashboardContactView;

/**
 * Created by jan on 5/8/17.
 */
public class DashboardContactPresenter implements DashboardItemPresenter {

    private DashboardContactView view = null;

    /**
     * Default DashboardPresenter Constructor.
     * @param view
     */
    public DashboardContactPresenter(DashboardContactView view) {
        this.view = view;
    }

    /**
     * @return The instance of the presenters view
     */
    public DashboardContactView getView() {
        return view;
    }
}
