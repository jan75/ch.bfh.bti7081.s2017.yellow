package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.entities.person.Person;
import ch.bfh.bti7081.s2017.yellow.services.SimpleService;
import ch.bfh.bti7081.s2017.yellow.services.SimpleServiceImpl;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactDetailView;
import com.vaadin.navigator.ViewChangeListener;

import java.util.Arrays;

/**
 * Presenter for a ContactDetailPresenter. Supports displaying and editing of a single contact.
 * @author iSorp
 */
public class ContactDetailPresenter implements ContactDetailView.ContactDetailViewListener {

    private ContactDetailView view;

    // Service to access contact data
    private SimpleService<ContactBookEntry> service = new SimpleServiceImpl<>();

    /**
     * Default ContactPresenter Constructor.
     *
     * @param view A instance of an concrete ContactDetailView
     * @author iSorp
     */
    public ContactDetailPresenter(ContactDetailView view) {
        this.view = view;

        view.addListener(this);
    }

    /**
     * @return The instance of the presenters view
     */
    public ContactDetailView getView() {
        return view;
    }

    /**
     *
     */
    public void displayContact(ContactBookEntryBean contactBookEntryBean) {
        view.setContact(contactBookEntryBean);
    }


    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    public void save() {
        // TODO: distinguish Person type
        ContactBookEntryBean bean = view.getContact();
        service.saveEntities(Arrays.asList(
                new ContactBookEntry(new Person(bean.getFirstName(), bean.getLastName()), bean.getPhoneNumber())));
    }

    @Override
    public void delete() {
        // TODO: Delete Entry by ID
        //contactBookEntryService.remove();
        navigateBack();
    }

    @Override
    public void cancel() {
        navigateBack();
    }

    private void navigateBack() {
        NavigatorController.getInstance().navigateTo("contactView");
    }
}
