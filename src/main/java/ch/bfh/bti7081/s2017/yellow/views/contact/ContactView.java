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
    void setDataProvider(List<ContactBookEntryBean> entries);

    interface ContactViewListener extends NavigatorViewListener{
        void setFilter(String filter);
        void selectionChange(ContactBookEntryBean selection);
        void addContact();
    }
    void addListener(ContactViewListener listener);
}
