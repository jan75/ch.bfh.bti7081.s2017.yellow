package ch.bfh.bti7081.s2017.yellow.util;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.UI;

/**
 * Created by simon on 08.05.17.
 */
public class NavigatorController {
    private static NavigatorController instance = new NavigatorController();
    private  Navigator navigator;

    public static NavigatorController getInstance() {
        return instance;
    }

    private NavigatorController() {
    }

    public void setUiContainer(UI ui) {
        navigator = new Navigator(ui, ui);
    }

    public void navigateTo(String view){
        navigator.navigateTo(view);
    }

    public void addView(String name, View view) {
        navigator.addView(name, view);
    }
}
