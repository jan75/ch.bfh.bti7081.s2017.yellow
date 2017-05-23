package ch.bfh.bti7081.s2017.yellow.views.planning;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Concrete PlanningView implementation
 * @author iSorp
 */
public class PlanningViewImpl extends CustomComponent implements PlanningView {

    public PlanningViewImpl() {
        final VerticalLayout layout = new VerticalLayout();

        setCompositionRoot(layout);
        setVisible(false);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setVisible(true);
    }

}
