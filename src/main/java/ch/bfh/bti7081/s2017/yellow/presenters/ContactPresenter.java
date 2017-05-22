package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.services.ContactService;
import ch.bfh.bti7081.s2017.yellow.services.SimpleService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactDetailView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactDetailViewImpl;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactView;
import com.vaadin.navigator.ViewChangeListener;

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
        ContactDetailView contactDetailView = new ContactDetailViewImpl();
        contactDetailPresenter = new ContactDetailPresenter(contactDetailView);

        // View listeners (add contact, change view)
        view.addListener(this);

        // Adding List of ContactBookEntryBeans to a data provider
        view.setDataProvider(service.getContactBookDataProvider());

        // View Navigator for detailed contact form
        NavigatorController.getInstance().addView("contactDetailView", contactDetailView);
    }

    /**
     * @return The instance of the presenters view
     */
    public ContactView getView() {
        return view;
    }

    /**
     * Sets a filter for displaying a certain contact category.
     * @param filter
     */
    public void setFilter(String filter) {
        service.setFilter(filter);
        service.getContactBookDataProvider().refreshAll();
    }
    /**
     * Displays the detail view for a single contact.
     */
    public void selectionChange(ContactBookEntryBean selection) {
        if (selection != null) {
            contactDetailPresenter.setContact(selection);
            NavigatorController.getInstance().navigateTo("contactDetailView");
        }
    }

    @Override
    public void addContact() {
        ContactBookEntryBean bean = new ContactBookEntryBean();
        bean.setEntity(new ContactBookEntry());
        contactDetailPresenter.setContact(new ContactBookEntryBean());
        NavigatorController.getInstance().navigateTo("contactDetailView");
    }

    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        service.getContactBookDataProvider().refreshAll();
    }
}