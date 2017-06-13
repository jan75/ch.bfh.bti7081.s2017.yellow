package ch.bfh.bti7081.s2017.yellow.util;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.UI;

/**
 * Represents a singleton controller for application navigation.
 * All listeners view must implement the interface NavigationView
 *
 * @author iSorp
 * @see NavigatorView
 */
public class NavigatorController {
    private static NavigatorController instance;
    private  Navigator navigator;

    /**
     * Default NavigatorController Constructor.
     */
    private NavigatorController() {}

    /**
     * @return The instance of the NavigatorController
     */
    public static NavigatorController getInstance() {
        if (instance == null) {
            instance = new NavigatorController();
        }
        return instance;
    }

    /**
     * Sets the view root container of the application
     * @param ui root component
     */
    public void setUiContainer(UI ui) {
        navigator = new Navigator(ui, ui);
    }

    /**
     * Navigates to parametrized view. The view is seen in the browsers address list.
     * @param view -
     */
    public void navigateTo(String view){
        navigator.navigateTo(view);
    }

    /**
     * Adds a navigator listener.
     * @param name -
     * @param view -
     */
    public void addView(String name, View view) {
        navigator.addView(name, view);
    }
}
