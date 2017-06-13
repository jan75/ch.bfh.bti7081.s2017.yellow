package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.beans.PersonBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningViewImpl;
import com.vaadin.ui.Component;

/**
 * Interface for a DetailView class. Supports public methods for the Presenter.
 * @author iSorp
 * @see ContactDetailViewImpl
 */
public interface ContactDetailView extends Component, NavigatorView {
    boolean validate();
    void setContact(ContactBookEntryBean  contactBookEntryBean);
    ContactBookEntryBean getContact();

    /**
     * Interface for the presenter, performs actions for the view
     */
    interface ContactDetailViewListener extends NavigatorViewListener {
        void saveClicked();
        void cancelClicked();
    }

    /**
     * Adds to Presenter as a listener to the view
     * @param listener
     */
    void addListener(ContactDetailViewListener listener);
}
