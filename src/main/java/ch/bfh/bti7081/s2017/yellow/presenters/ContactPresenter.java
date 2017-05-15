package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.ContactDetailView;
import ch.bfh.bti7081.s2017.yellow.views.ContactView;
import ch.bfh.bti7081.s2017.yellow.views.listeners.ContactViewListener;

/**
 * Presenter for a ContactView. Supports displaying and editing of Contacts fields.
 * @author iSorp
 */
public class ContactPresenter implements ContactViewListener {
    private ContactView view;
    private ContactDetailPresenter contactDetailPresenter;

    // Service to access contact data
    private ContactService service =  new ContactService();

    /**
     * Default ContactPresenter Constructor.
     * @param view A instance of an concrete ContactView
     */
    public ContactPresenter(ContactView view) {
        // concrete ContactView
        this.view = view;

        // SubView items
        contactDetailPresenter = new ContactDetailPresenter(new ContactDetailView());
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
        view.setContacts(service.getALlEntities());
    }

    /**
     * Sets a filter for displaying a certain contact category.
     * @param index
     */
    public void setFilter(int index) {

    }

    /**
     * Displays the detail view for a single contact.
     */
    public void showContactDetail() {
        //contactDetailPresenter.showContact(contact);
    }

}
