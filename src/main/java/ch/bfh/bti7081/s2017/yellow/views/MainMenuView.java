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


        menuLayout.addComponent(new MenuButton("Planning", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                NavigatorController.getInstance().navigateTo("planningView");
            }
        }));


        menuLayout.addComponent(new MenuButton("Contact", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                NavigatorController.getInstance().navigateTo("contactView");
            }
        }));


        menuLayout.addComponent(new MenuButton("Wiki", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                NavigatorController.getInstance().navigateTo("wikiView");
            }
        }));

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
