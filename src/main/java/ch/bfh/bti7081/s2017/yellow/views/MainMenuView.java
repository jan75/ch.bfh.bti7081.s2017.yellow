package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.components.MenuButton;
import ch.bfh.bti7081.s2017.yellow.presenters.*;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.listeners.MenuViewListener;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningView;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningViewImpl;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiView;
import ch.bfh.bti7081.s2017.yellow.views.wiki.WikiViewImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenuView extends CustomComponent implements MenuView, Button.ClickListener {

    private List<MenuViewListener> listeners;

    private Label title;

    public MainMenuView() {
        title = new Label("Menu");
        listeners = new ArrayList<>();
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout menuLayout = new HorizontalLayout();

        Image imagePlanning = new Image(null, new ThemeResource("icons/Planning.png"));
        imagePlanning.addClickListener(e -> NavigatorController.getInstance().navigateTo("planningView"));
        imagePlanning.setStyleName("image");
        menuLayout.addComponent(imagePlanning);

        Image imageContact = new Image(null, new ThemeResource("icons/Contacts.png"));
        imageContact.addClickListener(e -> NavigatorController.getInstance().navigateTo("contactView"));
        imageContact.setStyleName("image");
        menuLayout.addComponent(imageContact);

        Image imageWiki = new Image(null, new ThemeResource("icons/Wiki.png"));
        imageWiki.addClickListener(e -> NavigatorController.getInstance().navigateTo("wikiView"));
        imageWiki.setStyleName("image");
        menuLayout.addComponent(imageWiki);

        Image patientEstimationView = new Image(null, new ThemeResource("icons/Timer.png"));
        patientEstimationView.addClickListener(e -> NavigatorController.getInstance().navigateTo("patientEstimationView"));
        patientEstimationView.setStyleName("image");
        menuLayout.addComponent(patientEstimationView);

        layout.addComponent(menuLayout);

        DashboardView dashboardView = new DashboardViewImpl();
        new DashboardPresenter(dashboardView);
        layout.addComponent(dashboardView);
        layout.setSizeFull();

        setCompositionRoot(layout);
    }

    @Override
    public void setTitle(String title) {
        this.title.setValue(title);
    }

    @Override
    public void addListener(MenuViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        //
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        //
    }
}
