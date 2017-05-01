package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.views.listeners.MenuViewListener;

public interface MenuView {

    void setTitle(String title);

    void addListener(MenuViewListener listener);
}

