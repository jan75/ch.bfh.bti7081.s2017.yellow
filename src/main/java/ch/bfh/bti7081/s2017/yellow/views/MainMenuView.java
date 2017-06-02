package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.components.MenuButton;
import ch.bfh.bti7081.s2017.yellow.presenters.DashboardPresenter;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.listeners.MenuViewListener;
import com.vaadin.navigator.ViewChangeListener;
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
        listeners.forEach(listener -> {
            listener.navigate("a");
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        //
    }
}
