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
     *
     */
    public void setContact(ContactBookEntryBean contactBookEntryBean) {
        view.setContact(contactBookEntryBean);
    }

    private void navigateBack() {
        NavigatorController.getInstance().navigateTo("contactView");
    }

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

    @Override
    public void deleteClicked() {
        navigateBack();
    }

    @Override
    public void cancelClicked() {
        navigateBack();
    }

    @Override
    public void changeView(ViewChangeListener.ViewChangeEvent event) {
        service.LoadContactBook();
    }
}
