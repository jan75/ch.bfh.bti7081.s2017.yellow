package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.MenuView;
import ch.bfh.bti7081.s2017.yellow.views.listeners.MenuViewListener;


public class MainMenuPresenter implements MenuViewListener {

    private MenuView view;

    public MainMenuPresenter(MenuView view) {
        this.view = view;
        view.setTitle("Main menu");
        view.addListener(this);
    }

    @Override
    public void navigate(String operation) {

    }
}
