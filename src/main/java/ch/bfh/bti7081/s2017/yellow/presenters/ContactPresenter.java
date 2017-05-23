package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.EmployeeBean;
import ch.bfh.bti7081.s2017.yellow.beans.PatientBean;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.services.ContactService;
import ch.bfh.bti7081.s2017.yellow.services.SimpleService;
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

        // Adding List of ContactBookEntryBeans to a data provider
        view.setDataProvider(service.getContactBookEntries());

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
        service.getContactBookDataProvider().refreshAll();
    }
    /**
     * Displays the detail view for a single contact.
     */
    public void selectionChange(ContactBookEntryBean selection) {
        if (selection != null) {
            if (selection.getType().getName().equals(PatientBean.class.getName())) {
                activeDetailView = contactPatientPresenter;
            }else if (selection.getType().getName().equals(EmployeeBean.class.getName())) {
                activeDetailView = contactEmployeePresenter;
            }
            activeDetailView.setContact(selection);
            NavigatorController.getInstance().navigateTo(activeDetailView.getView().getClass().getName());
        }
    }

    @Override
    public void addContact() {
        ContactBookEntryBean bean = new ContactBookEntryBean();
        //bean.setEntity(new ContactBookEntry());
        activeDetailView.setContact(new ContactBookEntryBean());
        NavigatorController.getInstance().navigateTo(activeDetailView.getView().getClass().getName());
    }

    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        service.getContactBookDataProvider().refreshAll();
    }
}