package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.components.MenuButton;
import ch.bfh.bti7081.s2017.yellow.views.listeners.MenuViewListener;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenuView extends CustomComponent implements MenuView, Button.ClickListener {

    private List<MenuViewListener> listeners;

    private Label title;

    public MainMenuView() {
        title = new Label("Menu");
        listeners = new ArrayList<>();
        HorizontalLayout menuLayout = new HorizontalLayout();

        for(int i = 0; i <= 6; i++) {
            menuLayout.addComponent(new MenuButton("Menu "+ i, this));
        }
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
            listener.navigate("a");
        });
    }
}
