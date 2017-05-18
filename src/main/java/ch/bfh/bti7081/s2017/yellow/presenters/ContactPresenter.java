package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.services.ContactService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactDetailView;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactDetailViewImpl;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactView;
import com.vaadin.navigator.ViewChangeListener;

import java.util.Optional;

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

        // View listeners
        view.addListener(this);

        // Data provider for grid
        view.setDataProvider(service.getContactBookDataProvider());

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
            contactDetailPresenter.displayContact(selection);
            NavigatorController.getInstance().navigateTo("contactDetailView");
        }
    }

    @Override
    public void addContact() {
        contactDetailPresenter.displayContact(new ContactBookEntryBean());
        NavigatorController.getInstance().navigateTo("contactDetailView");
    }

    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        service.getContactBookDataProvider().refreshAll();
    }
}