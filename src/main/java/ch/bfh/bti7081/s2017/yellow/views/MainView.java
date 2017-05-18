package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.presenters.ContactPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.DashboardPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.MainMenuPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.PlanningDashboardItemPresenter;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepository;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;
import ch.bfh.bti7081.s2017.yellow.services.SimpleServiceImpl;

import java.sql.SQLException;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainView extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		try {
			CrudRepositoryImpl.initDbConnection();
		} catch (SQLException e) {
			System.out.println("Could not start db connection");
			throw new RuntimeException(e);
		}
		
		VerticalLayout rootLayout = new VerticalLayout();

		MainMenuView view = new MainMenuView();
		new MainMenuPresenter(view);
		rootLayout.addComponent(view);

		DashboardView dashboardView = new DashboardViewImpl();
		new DashboardPresenter(dashboardView);
		rootLayout.addComponent(dashboardView);

		setContent(rootLayout);
	}
}
