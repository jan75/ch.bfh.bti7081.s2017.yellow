package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * Created by simon on 16.05.17.
 */
public interface ContactDetailView extends Component, NavigatorView {
    void setContact(ContactBookEntryBean contactBookEntryBean);
    ContactBookEntryBean getContact();

    interface ContactDetailViewListener extends NavigatorViewListener {
        void save();
        void delete();
        void cancel();
    }
    void addListener(ContactDetailViewListener listener);
}
