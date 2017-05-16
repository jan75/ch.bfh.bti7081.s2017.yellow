package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.services.ContactService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import ch.bfh.bti7081.s2017.yellow.views.ContactDetailView;
import ch.bfh.bti7081.s2017.yellow.views.ContactDetailViewImpl;
import ch.bfh.bti7081.s2017.yellow.views.ContactView;
import com.vaadin.navigator.ViewChangeListener;

import java.awt.*;
import java.util.Set;

/**
 * Presenter for a ContactView. Supports displaying and editing of Contacts fields.
 * @author iSorp
 */
public class ContactPresenter implements ContactView.ContactViewListener {
    private ContactView view;
    private ContactDetailPresenter contactDetailPresenter;

    // Service to access contact data
    private ContactService service = new ContactService();

    /**
     * Default ContactPresenter Constructor.
     * @param view A instance of an concrete ContactView
     */
    public ContactPresenter(ContactView view) {

        // concrete ContactView
        this.view = view;

        // SubView items
        contactDetailPresenter = new ContactDetailPresenter(view.getContactDetailView());

        // View listeners
        view.addListener(this);
    }

    /**
     * @return The instance of the presenters view
     */
    public ContactView getView() {
        return view;
    }

    /**
     * Displays all contacts
     */
    public void displayContacts() {
        try {
            view.setContacts(service.getContactBookEntries());
        }catch (Exception exception) {
            // TODO: MessageBox
        }
    }

    /**
     * Sets a filter for displaying a certain contact category.
     * @param filter
     */
    public void setFilter(String filter) {
        service.setFilter(filter);
    }

    /**
     * Displays the detail view for a single contact.
     */
    public void showContactDetail() {

        Set<ContactBookEntry> e = view.getSelectedContactBookEntry();
        contactDetailPresenter.displayContact(e.toArray(new ContactBookEntry[]{})[0]);
    }

    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        displayContacts();
    }
}