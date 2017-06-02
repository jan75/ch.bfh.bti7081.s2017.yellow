package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import ch.bfh.bti7081.s2017.yellow.views.listeners.MenuViewListener;
import com.vaadin.ui.Component;

public interface MenuView extends Component, NavigatorView {

    void setTitle(String title);

    void addListener(MenuViewListener listener);
}

