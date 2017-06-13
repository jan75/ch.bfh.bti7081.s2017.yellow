package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookBean;
import ch.bfh.bti7081.s2017.yellow.beans.PersonBean;
import ch.bfh.bti7081.s2017.yellow.services.ContactService;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorController;
import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.views.contact.ContactDetailView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;
import javassist.NotFoundException;

/**
 * Presenter for a ContactDetailPresenter. Supports displaying and editing of a single contact.
 * @author iSorp
 */
public class ContactDetailPresenter<TView extends ContactDetailView> implements ContactDetailView.ContactDetailViewListener {

    private TView view;

    // Service to access contact data
    private ContactService service = new ContactService();

    /**
     * Default ContactPresenter Constructor.
     *
     * @param view A instance of an concrete ContactDetailView
     * @author iSorp
     */
    public ContactDetailPresenter(TView view) {
        this.view = view;

        view.addListener(this);
    }

    /**
     * @return The instance of the presenters view
     */
    public TView getView() {
        return view;
    }

    /**
     * Wires a ContactBookEntry to the view
     */
    public void setContact(ContactBookEntryBean contactBookEntryBean) {
        view.setContact(contactBookEntryBean);
    }

    /**
     * Navigates back to the parent view
     */
    private void navigateBack() {
        NavigatorController.getInstance().navigateTo("contactView");
    }

    /**
     * Saves a ContactBookEntry to the database,
     * if the entity is not found a message will be displayed.
     */
    @Override
    public void saveClicked() {
        if (view.validate()) {
            try {
                service.saveContact(view.getContact());
            }
            catch (NotFoundException notFoundException){
                Notification.show(notFoundException.getMessage());
            }
            navigateBack();
        }
    }

    /**
     * Cancels the editing and navigates back to to parent view
     */
    @Override
    public void cancelClicked() {
        navigateBack();
    }

    /**
     * This Method is called when the view navigator calls this view
     * @param event
     */
    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        service.LoadContactBook();
    }
}
