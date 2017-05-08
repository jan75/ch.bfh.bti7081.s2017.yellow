package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.presenters.ContactPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.DashboardPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.MainMenuPresenter;
import ch.bfh.bti7081.s2017.yellow.services.SimpleServiceImpl;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainView extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout rootLayout = new VerticalLayout();

        MainMenuView view = new MainMenuView();
        new MainMenuPresenter(view);
        rootLayout.addComponent(view);

        HorizontalLayout infoLayout = new HorizontalLayout();
        ContactViewImpl contactView = new ContactViewImpl();
        new ContactPresenter(contactView, new SimpleServiceImpl<>());
        infoLayout.addComponent(contactView);
        rootLayout.addComponent(infoLayout);

        DashboardView dashboardView = new DashboardViewImpl();
        new DashboardPresenter(dashboardView);
        rootLayout.addComponent(dashboardView);

        String x = "hello";

        setContent(rootLayout);
    }
}
