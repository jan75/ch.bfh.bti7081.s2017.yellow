package ch.bfh.bti7081.s2017.yellow.util;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

/**
 * Interface for a navigatable View. contains the interface NavigatorViewListener
 * for Presenters.
 * All listeners view must implement the interface NavigationView
 *
 * @author iSorp
 * @see NavigatorView
 */
public interface NavigatorView extends View {
    interface NavigatorViewListener {
        void changeView(ViewChangeEvent event);
    }
    //void addListener(NavigatorViewListener listener);
}