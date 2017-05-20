package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;
import ch.bfh.bti7081.s2017.yellow.services.ContactService;
import ch.bfh.bti7081.s2017.yellow.services.SimpleService;
import ch.bfh.bti7081.s2017.yellow.services.SimpleServiceImpl;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactDetailView;
import com.vaadin.navigator.ViewChangeListener;

/**
 * Presenter for a ContactDetailPresenter. Supports displaying and editing of a single contact.
 * @author iSorp
 */
public class ContactDetailPresenter implements ContactDetailView.ContactDetailViewListener {

    private ContactDetailView view;

    // Service to access contact data
    private ContactService service = new ContactService();

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
    public void setContact(ContactBookEntryBean contactBookEntryBean) {
        view.setContact(contactBookEntryBean);
    }

    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        //view.validate();
    }

    @Override
    public void saveClicked() {
        // TODO: distinguish Person type
        if (view.validate()) {
            //service.saveEntities(Arrays.asList(
            //       new ContactBookEntry(new Person(bean.getFirstName(), bean.getLastName()), bean.getPhoneNumber())));
            navigateBack();
        }
    }

    @Override
    public void deleteClicked() {
        // TODO: Delete
        navigateBack();
    }

    @Override
    public void cancelClicked() {
        navigateBack();
    }


    private void navigateBack() {
        NavigatorController.getInstance().navigateTo("contactView");
    }
}
