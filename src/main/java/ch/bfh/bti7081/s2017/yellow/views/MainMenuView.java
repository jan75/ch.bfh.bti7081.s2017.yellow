package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.components.MenuButton;
import ch.bfh.bti7081.s2017.yellow.components.Test;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.listeners.MenuViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import java.util.ArrayList;
import java.util.List;

public class MainMenuView extends CustomComponent implements MenuView, Button.ClickListener {

    private List<MenuViewListener> listeners;

    private Label title;

    public MainMenuView() {
        title = new Label("Menu");
        listeners = new ArrayList<>();
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
                //NavigatorController.getInstance().navigateTo("wikiView");
            }
        }));

        Test test = new Test();
        menuLayout.addComponent(test);

        setCompositionRoot(menuLayout);
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
            NavigatorController.getInstance().navigateTo("planningView");
        });
    }
}
