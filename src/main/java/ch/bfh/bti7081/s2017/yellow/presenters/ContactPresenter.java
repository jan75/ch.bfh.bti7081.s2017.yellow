package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeeBean;
import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;
import ch.bfh.bti7081.s2017.yellow.services.ContactService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.views.contact.*;
import com.vaadin.navigator.ViewChangeListener;

/**
 * Presenter for a ContactView. Supports displaying and editing of Contacts fields.
 * @author iSorp
 */
public class ContactPresenter implements ContactView.ContactViewListener {
    private ContactView view;
    private ContactDetailPresenter activeDetailView;
    private ContactDetailPresenter contactPatientPresenter;
    private ContactDetailPresenter contactEmployeePresenter;

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
        ContactDetailView contactPatientView = new PatientViewImpl();
        ContactDetailView contactEmployeeView = new EmployeeViewImpl();
        contactPatientPresenter = new ContactDetailPresenter<ContactDetailView>(contactPatientView);
        contactEmployeePresenter = new ContactDetailPresenter<ContactDetailView>(contactEmployeeView);

        // View listeners (add contact, change view)
        view.addListener(this);

        // View Navigator for detailed contact form
        NavigatorController.getInstance().addView(contactEmployeeView.getClass().getName(), contactEmployeeView);
        NavigatorController.getInstance().addView(contactPatientView.getClass().getName(), contactPatientView);
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
        view.setContactBookEntries(service.getContactBookEntries());
    }

    /**
     * Displays the detail view for a single contact.
     * @param contact selected contactBookEntry
     */
    public void selectionChange(ContactBookEntryBean contact) {
        if (contact != null) {
            if (contact.getPerson() instanceof PatientBean) {
                activeDetailView = contactPatientPresenter;
            }else if (contact.getPerson() instanceof EmployeeBean) {
                activeDetailView = contactEmployeePresenter;
            }
            activeDetailView.setContact(contact);
            NavigatorController.getInstance().navigateTo(activeDetailView.getView().getClass().getName());
        }
    }

    /**
     * Is called when the attached view is displayed.
     * Loading all data from database and display it
     * @param event
     */
    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        service.LoadContactBook();
        view.setContactBookEntries(service.getContactBookEntries());
    }
}