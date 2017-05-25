package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.presenters.ContactPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.DashboardPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.MainMenuPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.WikiPresenter;
import ch.bfh.bti7081.s2017.yellow.repositories.CrudRepositoryImpl;
import java.sql.SQLException;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactViewImpl;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiView;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiViewImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
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

       /* ContactView contactView = new ContactViewImpl();
        new ContactPresenter(contactView);
        rootLayout.addComponent(contactView);
        NavigatorController.getInstance().addView("contactView", contactView);
        setContent(rootLayout);
        NavigatorController.getInstance().navigateTo("contactView");*/

		WikiView wikiView = new WikiViewImpl();
		new WikiPresenter(wikiView);
		rootLayout.addComponent(wikiView);
		NavigatorController.getInstance().addView("wikiView", wikiView);
		setContent(rootLayout);
		NavigatorController.getInstance().navigateTo("wikiView");
    }
}
