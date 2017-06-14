package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * Interface for a ContactViewImpl class. Supports public methods for the Presenter.
 * @author iSorp
 * @see ContactViewImpl
 */
public interface ContactView extends Component, NavigatorView  {
    void setContactBookEntries(List<ContactBookEntryBean> entries);

    /**
     * Interface for the presenter, performs actions for the view
     */
    interface ContactViewListener extends NavigatorViewListener{
        void setFilter(String filter);
        void selectionChange(ContactBookEntryBean selection);
    }

    /**
     * Adds to Presenter as a listener to the view
     * @param listener
     */
    void addListener(ContactViewListener listener);
}
