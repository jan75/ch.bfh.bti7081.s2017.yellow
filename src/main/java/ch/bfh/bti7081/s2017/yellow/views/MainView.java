package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.presenters.ContactPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.MainMenuPresenter;
import ch.bfh.bti7081.s2017.yellow.presenters.WikiPresenter;
import ch.bfh.bti7081.s2017.yellow.repositories.DbConnector;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactViewImpl;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiView;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiViewImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.sql.SQLException;

@Theme("mytheme")
public class MainView extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
		try {
			DbConnector.initDbConnection();
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

		WikiView wikiView = new WikiViewImpl();
		WikiPresenter wikiPresenter =  new WikiPresenter(wikiView);
		rootLayout.addComponent(wikiView);

		NavigatorController.getInstance().addView("", view);
		NavigatorController.getInstance().addView("contactView", contactView);
		NavigatorController.getInstance().addView("wikiView", wikiView);
		setContent(rootLayout);
    }
}
