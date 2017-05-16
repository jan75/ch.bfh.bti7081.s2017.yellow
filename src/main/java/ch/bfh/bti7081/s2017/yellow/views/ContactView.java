package ch.bfh.bti7081.s2017.yellow.views;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * Interface for a ContactViewImpl class. Supports public methods for the Presenter.
 * @author iSorp
 * @see ContactViewImpl
 */
public interface ContactView extends NavigatorView, Component {
    void setContacts(List<ContactBookEntry> contact);
    Set<ContactBookEntry> getSelectedContactBookEntry();
    ContactDetailView getContactDetailView();

    interface ContactViewListener extends NavigatorViewListener{
        void setFilter(String filter);
    }
    void addListener(ContactViewListener listener);
}
