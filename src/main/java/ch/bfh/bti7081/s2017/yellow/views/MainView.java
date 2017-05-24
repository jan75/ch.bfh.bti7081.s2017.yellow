package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.presenters.*;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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

		MainMenuView view = new MainMenuView();
		new MainMenuPresenter(view);
		rootLayout.addComponent(view);

        DashboardView dashboardView = new DashboardViewImpl();
        new DashboardPresenter(dashboardView);
        rootLayout.addComponent(dashboardView);

        // @Todo: Implement Navigator! Isn't the right place
        EstimateResourceView estimateResourceView = new EstimateResourceViewImpl();
        new EstimateResourcePresenter(estimateResourceView);
        rootLayout.addComponent(estimateResourceView);

        setContent(rootLayout);
        NavigatorController.getInstance().navigateTo("contactView");
    }
}
