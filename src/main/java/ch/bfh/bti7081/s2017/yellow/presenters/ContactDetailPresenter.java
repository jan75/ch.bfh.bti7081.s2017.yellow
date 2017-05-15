package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.ContactDetailView;

/**
 * Presenter for a ContactDetailPresenter. Supports displaying and editing of a single contact.
 * @author iSorp
 */
public class ContactDetailPresenter {

    private ContactDetailView view;
    /**
     * Default ContactPresenter Constructor.
     * @author iSorp
     * @param view A instance of an concrete ContactDetailView
     */
    public ContactDetailPresenter(ContactDetailView view) {
        this.view = view;
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
    public void displayContact() {

    }

}
