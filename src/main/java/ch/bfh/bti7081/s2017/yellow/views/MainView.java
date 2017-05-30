package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.presenters.ContactPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.DashboardPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.MainMenuPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.PlanningPresenter;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactViewImpl;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningView;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningViewImpl;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import elemental.html.Navigator;

import java.sql.SQLException;

public class MainView extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
		try {
			CrudRepositoryImpl.initDbConnection();
		} catch (SQLException e) {
			System.out.println("Could not start db connection");
			throw new RuntimeException(e);
		}
	
        NavigatorController.getInstance().setUiContainer(this);
        VerticalLayout rootLayout = new VerticalLayout();

		MenuView view = new MainMenuView();
		new MainMenuPresenter(view);
		rootLayout.addComponent(view);

        ContactView contactView = new ContactViewImpl();
        new ContactPresenter(contactView);

        PlanningView planningView = new PlanningViewImpl();
		new PlanningPresenter(planningView);

		NavigatorController.getInstance().addView("", view);
        NavigatorController.getInstance().addView("contactView", contactView);
        NavigatorController.getInstance().addView("planningView", planningView);



        setContent(rootLayout);
    }
}