package ch.bfh.bti7081.s2017.yellow.views.contact;

import ch.bfh.bti7081.s2017.yellow.beans.ContactBookEntryBean;
import ch.bfh.bti7081.s2017.yellow.beans.PersonBean;
import ch.bfh.bti7081.s2017.yellow.util.NavigatorView;
import ch.bfh.bti7081.s2017.yellow.views.planning.PlanningViewImpl;
import com.vaadin.ui.Component;

/**
 * Created by simon on 16.05.17.
 */
public interface ContactDetailView extends Component, NavigatorView {
    boolean validate();
    void setContact(ContactBookEntryBean  contactBookEntryBean);
    ContactBookEntryBean getContact();
    interface ContactDetailViewListener extends NavigatorViewListener {
        void saveClicked();
        void deleteClicked();
        void cancelClicked();
    }
    void addListener(ContactDetailViewListener listener);
}
